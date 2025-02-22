package pe.globant.assestment.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.globant.assestment.api.entities.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {
}
