package fr.btn.BookMyTicketAPI.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
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

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private Date releasedDate;

    @Column(nullable = false)
    private int duration;

    private int rating;

    @ManyToMany
    @JoinTable(
            name = "movieGenres",
            joinColumns = @JoinColumn(name="movie_id"),
            inverseJoinColumns = @JoinColumn(name="genre_id")
    )
    private Set<GenreEntity> movieGenres;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nationality")
    private NationEntity nationEntity;

    @Column(length = 2500)
    private String description;

    private String bannerUrl;

    @OneToMany(mappedBy = "movie")
    Set<MovieCrewEntity> movieCrew;
}
