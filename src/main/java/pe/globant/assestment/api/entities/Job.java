package pe.globant.assestment.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "job")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Job {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "job")
    private String job_name;
}
