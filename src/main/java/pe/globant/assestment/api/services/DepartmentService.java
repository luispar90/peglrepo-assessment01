package pe.globant.assestment.api.services;

import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.globant.assestment.api.entities.Department;
import pe.globant.assestment.api.repositories.DepartmentRepository;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public void uploadDepartment(MultipartFile file) throws Exception{
        try(Reader reader = new InputStreamReader(file.getInputStream());
            CSVReader csvReader = new CSVReader(reader)){

            List<Department> departments = csvReader.readAll().stream()
                    .map(data -> new Department(
                            Integer.parseInt(data[0]),
                            data[1]))
                    .collect(Collectors.toList());

            departmentRepository.saveAll(departments);
        }

    }
}
