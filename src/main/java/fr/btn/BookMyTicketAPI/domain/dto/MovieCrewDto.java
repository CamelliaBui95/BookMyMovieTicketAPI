package fr.btn.BookMyTicketAPI.domain.dto;

import fr.btn.BookMyTicketAPI.domain.entities.MovieEntity;
import fr.btn.BookMyTicketAPI.domain.entities.PersonEntity;
import fr.btn.BookMyTicketAPI.domain.entities.embeddedKeys.MovieCrewKey;
import fr.btn.BookMyTicketAPI.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieCrewDto {
    private MovieCrewKey id;
    private MovieEntity movie;
    private PersonEntity person;
    private Role role;
}
