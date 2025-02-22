package pe.globant.assestment.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.globant.assestment.api.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
