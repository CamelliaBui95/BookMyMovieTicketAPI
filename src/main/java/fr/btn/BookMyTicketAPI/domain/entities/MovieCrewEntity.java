package fr.btn.BookMyTicketAPI.domain.entities;

import fr.btn.BookMyTicketAPI.domain.entities.embeddedKeys.MovieCrewKey;
import fr.btn.BookMyTicketAPI.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movieCrew")
public class MovieCrewEntity {

    @EmbeddedId
    private MovieCrewKey id;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @Column(length = 25, nullable = false)
    private Role role;
}
