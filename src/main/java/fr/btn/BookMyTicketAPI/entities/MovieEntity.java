package fr.btn.BookMyTicketAPI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_generator")
    @SequenceGenerator(name = "movie_generator", sequenceName = "movie_id_movie_seq", allocationSize = 1)
    @Column(name = "id_movie")
    private Long id;

    private String title;
    private String description;

    @Column(name="img_url")
    private String imgUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "released_date")
    private LocalDate releasedDate;
    private int duration;
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nationality")
    private NationEntity nationality;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name="id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_genre")
    )
    private Set<GenreEntity> genres;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private Set<ProdInfoEntity> productionInfo;
}
