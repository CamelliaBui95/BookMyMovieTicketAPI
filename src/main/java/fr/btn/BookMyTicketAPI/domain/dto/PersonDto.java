package fr.btn.BookMyTicketAPI.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.btn.BookMyTicketAPI.domain.entities.MovieCrewEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {
    private Long id;
    private String firstname;
    private String lastname;

}
