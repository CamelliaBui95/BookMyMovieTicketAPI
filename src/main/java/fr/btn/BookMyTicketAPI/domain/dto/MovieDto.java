package fr.btn.BookMyTicketAPI.domain.dto;

import fr.btn.BookMyTicketAPI.domain.entities.GenreEntity;
import fr.btn.BookMyTicketAPI.domain.entities.MovieCrewEntity;
import fr.btn.BookMyTicketAPI.domain.entities.NationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate releasedDate;
    private NationEntity nationEntity;
    private String bannerUrl;
    private int duration;
    private int rating;
    private Set<GenreEntity> genres;
    private Set<MovieCrewEntity> movieCrew;
}
