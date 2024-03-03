package fr.btn.BookMyTicketAPI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="production_crew")
public class ProdCrewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_crew_generator")
    @SequenceGenerator(name = "prod_crew_generator", sequenceName = "production_crew_id_seq", allocationSize = 1)
    private Long id;
    private String name;
}
