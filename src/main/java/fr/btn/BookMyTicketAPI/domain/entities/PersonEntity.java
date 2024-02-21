package fr.btn.BookMyTicketAPI.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private Set<MovieCrewEntity> movieCrew;
}
