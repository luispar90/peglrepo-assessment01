package pe.globant.assestment.api.services;

import com.opencsv.CSVReader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.globant.assestment.api.entities.Job;
import pe.globant.assestment.api.repositories.JobRepository;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    @Transactional
    public void uploadJob(MultipartFile file) throws Exception{
        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVReader csvReader = new CSVReader(reader)) {

            List<Job> jobs = csvReader.readAll().stream()
                    .map(data -> new Job(
                            Integer.parseInt(data[0]),
                            data[1]))
                    .collect(Collectors.toList());

            jobRepository.saveAll(jobs);
        }
    }
}
