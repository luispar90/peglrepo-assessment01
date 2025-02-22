package pe.globant.assestment.api.services;

import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.globant.assestment.api.dto.EmployeeHired2021Dto;
import pe.globant.assestment.api.dto.EmployeeHiredByDepartmentDto;
import pe.globant.assestment.api.entities.Employee;
import pe.globant.assestment.api.repositories.DepartmentRepository;
import pe.globant.assestment.api.repositories.EmployeeRepository;
import pe.globant.assestment.api.repositories.JobRepository;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final JobRepository jobRepository;

    public void uploadEmployees(MultipartFile file) throws Exception {
        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVReader csvReader = new CSVReader(reader)) {

                List<Employee> employees = csvReader.readAll().stream()
                        .map(data -> new Employee(
                                Integer.parseInt(data[0]),
                                data[1],
                                data[2],
                                data[3].trim().isEmpty() ? null : departmentRepository.findById(Integer.parseInt(data[3])).orElse(null),
                                data[4].trim().isEmpty() ? null : jobRepository.findById(Integer.parseInt(data[4])).orElse(null)))
                        .collect(Collectors.toList());

                employeeRepository.saveAll(employees);

            }
    }

    public List<EmployeeHired2021Dto> getHiredEmployeesByQuarter() {
        return employeeRepository.getHiredEmployeesByQuarter();
    }

    public List<EmployeeHiredByDepartmentDto> getMeanTotalHiredEmployeeByDepartment() {
        return employeeRepository.getMeanTotalHiredEmployeeByDepartment();
    }
}
