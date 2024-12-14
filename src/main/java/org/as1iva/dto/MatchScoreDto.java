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

    private int firstPlayerSets;
    private int secondPlayerSets;

    private int firstPlayerGames;
    private int secondPlayerGames;

    private int firstPlayerPoints;
    private int secondPlayerPoints;
}

