package org.as1iva.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class MatchResponseDto {

    private final PlayerResponseDto firstPlayer;

    private final PlayerResponseDto secondPlayer;

    private final PlayerResponseDto winner;
}
