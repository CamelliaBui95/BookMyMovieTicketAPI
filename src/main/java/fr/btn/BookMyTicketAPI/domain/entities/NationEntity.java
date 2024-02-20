package fr.btn.BookMyTicketAPI.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="nations")
public class NationEntity {
    @Id
    @Column(length = 2)
    private String code;

    @Column(nullable = false)
    private String name;
}
