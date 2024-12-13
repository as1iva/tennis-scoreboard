package org.as1iva.dto;

import lombok.*;
import org.as1iva.entity.Player;

@Getter
@Builder
@AllArgsConstructor
public class MatchScoreDto {

    private final Player firstPlayer;

    private final Player secondPlayer;

    private final int sets;

    private final int games;

    private final int points;

}

