package fr.btn.BookMyTicketAPI.domain.dto;

import fr.btn.BookMyTicketAPI.domain.entities.GenreEntity;
import fr.btn.BookMyTicketAPI.domain.entities.MovieCrewEntity;
import fr.btn.BookMyTicketAPI.domain.entities.NationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    private NationDto nationality;
    private String bannerUrl;
    private int duration;
    private int rating;

    private List<GenreDto> genres;
    private List<PersonDto> directors;
    private List<PersonDto> stars;

    public void addDirector(PersonDto personDto) {
        if(directors == null)
            directors = new ArrayList<>();

        directors.add(personDto);
    }

    public void addStar(PersonDto personDto) {
        if(stars == null)
            stars = new ArrayList<>();

        stars.add(personDto);
    }

    public void addGenre(GenreDto genreDto) {
        if(genres == null)
            genres = new ArrayList<>();

        genres.add(genreDto);
    }
}
