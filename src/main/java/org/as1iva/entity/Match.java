package org.as1iva.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_player")
    private Integer firstPlayer;

    @Column(name = "second_player")
    private Integer secondPlayer;

    private Integer winner;
}
