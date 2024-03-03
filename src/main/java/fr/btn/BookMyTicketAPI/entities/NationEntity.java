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
@Table(name="nation")
public class NationEntity {
    @Id
    @Column(name="code",length = 2)
    private String code;

    @Column(name="name",nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "nationality")
    private Set<MovieEntity> movies;

}
