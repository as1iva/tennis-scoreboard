package org.as1iva.dto;

import lombok.*;
import org.as1iva.entity.Player;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class MatchScoreDto {

    private Player firstPlayer;

    private Player secondPlayer;

    private int sets;

    private int games;

    private int points;
}

