package fr.btn.BookMyTicketAPI.domain.dto.embeddedDtoKeys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieCrewKeyDto {
    private Long movieId;
    private Long personId;
}
