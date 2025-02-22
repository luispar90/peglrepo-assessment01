package pe.globant.assestment.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "department")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "department")
    private String department;
}
