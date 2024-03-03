package fr.btn.BookMyTicketAPI.entities.compositeKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class ProductionPK {
    @Column(name = "id_movie")
    private Long movieId;

    @Column(name="id")
    private Long productionId;

}
