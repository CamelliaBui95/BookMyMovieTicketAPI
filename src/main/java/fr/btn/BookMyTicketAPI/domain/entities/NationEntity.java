package fr.btn.BookMyTicketAPI.domain.entities;

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
@Table(name="nations")
public class NationEntity {
    @Id
    @Column(length = 2)
    private String code;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "nationality")
    private Set<MovieEntity> movies;
}
