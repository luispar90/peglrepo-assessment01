package pe.globant.assestment.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.globant.assestment.api.dto.EmployeeHired2021Dto;
import pe.globant.assestment.api.dto.EmployeeHiredByDepartmentDto;
import pe.globant.assestment.api.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadEmployees(@RequestParam("file") MultipartFile file) {
        try {
            employeeService.uploadEmployees(file);
            return ResponseEntity.ok("Employees uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading employees: " + e.getMessage());
        }
    }

    @GetMapping("/get/by-quarter")
    public ResponseEntity<List<EmployeeHired2021Dto>> getHiredEmployeesByQuarter() {
        return ResponseEntity.ok(employeeService.getHiredEmployeesByQuarter());
    }

    @GetMapping("/get/by-mean-department")
    public ResponseEntity<List<EmployeeHiredByDepartmentDto>> getMeanTotalHiredEmployeeByDepartment() {
        return ResponseEntity.ok(employeeService.getMeanTotalHiredEmployeeByDepartment());
    }
}
