package pe.globant.assestment.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.globant.assestment.api.dto.EmployeeHired2021Dto;
import pe.globant.assestment.api.dto.EmployeeHiredByDepartmentDto;
import pe.globant.assestment.api.entities.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = """
        SELECT
            d.department AS department,
            j.job AS job,
            SUM(CASE WHEN EXTRACT(QUARTER FROM NULLIF(he.datetime, '')::TIMESTAMPTZ) = 1 THEN 1 ELSE 0 END) AS Q1,
            SUM(CASE WHEN EXTRACT(QUARTER FROM NULLIF(he.datetime, '')::TIMESTAMPTZ) = 2 THEN 1 ELSE 0 END) AS Q2,
            SUM(CASE WHEN EXTRACT(QUARTER FROM NULLIF(he.datetime, '')::TIMESTAMPTZ) = 3 THEN 1 ELSE 0 END) AS Q3,
            SUM(CASE WHEN EXTRACT(QUARTER FROM NULLIF(he.datetime, '')::TIMESTAMPTZ) = 4 THEN 1 ELSE 0 END) AS Q4
        FROM hired_employee he INNER JOIN department d ON he.department_id = d.id
        INNER JOIN job j ON he.job_id = j.id
        WHERE EXTRACT(YEAR FROM NULLIF(he.datetime, '')::TIMESTAMPTZ) = 2021
        GROUP BY d.department, j.job
        ORDER BY d.department, j.job
    """, nativeQuery = true)
    List<EmployeeHired2021Dto> getHiredEmployeesByQuarter();

    @Query(value = """
        WITH department_hiring AS (
            SELECT 
                d.id, 
                d.department AS name, 
                COUNT(he.id) AS total_hired
            FROM hired_employee he
            INNER JOIN department d ON he.department_id = d.id
            WHERE EXTRACT(YEAR FROM NULLIF(he.datetime, '')::TIMESTAMPTZ) = 2021
            GROUP BY d.id, d.department
        ),
        mean_hiring AS (
            SELECT AVG(total_hired) AS avg_hired FROM department_hiring
        )
        SELECT dh.id, dh.name, dh.total_hired
        FROM department_hiring dh, mean_hiring mh
        WHERE dh.total_hired > mh.avg_hired
        ORDER BY dh.total_hired DESC
    """, nativeQuery = true
    )
    List<EmployeeHiredByDepartmentDto> getMeanTotalHiredEmployeeByDepartment();
}
