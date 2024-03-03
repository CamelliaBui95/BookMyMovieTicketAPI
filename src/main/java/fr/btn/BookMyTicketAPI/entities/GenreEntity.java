package fr.btn.BookMyTicketAPI.entities;

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
@Entity
@Table(name="genre")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_generator")
    @SequenceGenerator(name = "genre_generator", sequenceName = "genre_id_genre_seq", allocationSize = 1)
    @Column(name = "id_genre", unique = true, nullable = false)
    private Long id;

    @Column(name="genre_label")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "genres")
    private Set<MovieEntity> movies;

}
