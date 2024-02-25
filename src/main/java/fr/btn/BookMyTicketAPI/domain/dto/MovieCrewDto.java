package fr.btn.BookMyTicketAPI.domain.dto;

import fr.btn.BookMyTicketAPI.domain.dto.embeddedDtoKeys.MovieCrewKeyDto;
import fr.btn.BookMyTicketAPI.domain.entities.MovieEntity;
import fr.btn.BookMyTicketAPI.domain.entities.PersonEntity;
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
    private MovieCrewKeyDto id;
    private MovieDto movie;
    private PersonDto person;
    private Role role;
}
