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
    private Long firstPlayer;

    @Column(name = "second_player")
    private Long secondPlayer;

    private Long winner;
}
