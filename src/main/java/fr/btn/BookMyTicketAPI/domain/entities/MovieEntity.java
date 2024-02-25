package fr.btn.BookMyTicketAPI.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movies")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(length = 2500)
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate releasedDate;

    @ManyToOne
    @JoinColumn(name = "nationality" )
    private NationEntity nationality;

    private String bannerUrl;

    @Column(nullable = false)
    private int duration;

    private int rating;

    @ManyToMany
    @JoinTable(
            name = "movieGenres",
            joinColumns = @JoinColumn(name="movie_id"),
            inverseJoinColumns = @JoinColumn(name="genre_id")
    )
    private Set<GenreEntity> genres;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private Set<MovieCrewEntity> movieCrew;


}
