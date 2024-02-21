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
@Builder
@Entity
@Table(name="genres")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_id_seq")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(fetch= FetchType.LAZY, mappedBy = "movieGenres")
    private Set<MovieEntity> movies;
}
