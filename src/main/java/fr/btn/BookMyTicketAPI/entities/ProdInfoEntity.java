package fr.btn.BookMyTicketAPI.entities;

import fr.btn.BookMyTicketAPI.entities.compositeKeys.ProductionPK;
import fr.btn.BookMyTicketAPI.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "production_info")
public class ProdInfoEntity {

    @EmbeddedId
    private ProductionPK productionPK;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "id_movie")
    private MovieEntity movie;

    @ManyToOne
    @MapsId("productionId")
    @JoinColumn(name = "id")
    private ProdCrewEntity prodCrew;

    private Role role;
}
