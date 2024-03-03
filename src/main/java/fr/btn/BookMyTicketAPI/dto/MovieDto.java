package fr.btn.BookMyTicketAPI.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private String imgUrl;
    private LocalDate releasedDate;
    private int duration;
    private int rating;

    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private NationDto nationality;

    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private Set<GenreDto> genres;

    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private List<ProdCrewDto> stars;

    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private ProdCrewDto director;

    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private ProdCrewDto producer;
}
