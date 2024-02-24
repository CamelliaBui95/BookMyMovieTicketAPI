package fr.btn.BookMyTicketAPI.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.btn.BookMyTicketAPI.domain.entities.MovieEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDto {
    private Long id;
    private String name;

    /*@JsonIgnore
    private Set<MovieEntity> movies;*/
}
