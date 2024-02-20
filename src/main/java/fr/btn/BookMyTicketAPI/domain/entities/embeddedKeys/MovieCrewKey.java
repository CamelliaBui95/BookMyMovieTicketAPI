package fr.btn.BookMyTicketAPI.domain.entities.embeddedKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MovieCrewKey implements Serializable {

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "person_id")
    private Long personId;
}
