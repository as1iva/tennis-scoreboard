package org.as1iva.service;

import org.as1iva.dto.MatchScoreDto;
import org.as1iva.entity.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MatchScoreCalculationServiceTest {
    private final MatchScoreDto matchScoreDto = MatchScoreDto.builder()
            .firstPlayer(Player.builder()
                    .id(0L)
                    .name("Tim")
                    .build())
            .secondPlayer(Player.builder()
                    .id(1L)
                    .name("Vlad")
                    .build())
            .build();

    private final MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(matchScoreDto);

    @Test
    void dontEndGameIfPlayerWinsPointAtDeuce() {
        matchScoreDto.setFirstPlayerPoints(3);
        matchScoreDto.setSecondPlayerPoints(3);

        matchScoreCalculationService.countScore(matchScoreDto.getFirstPlayer().getId());

        assertAll(
                () -> assertThat(matchScoreDto.getFirstPlayerGames()).isEqualTo(0),
                () -> assertThat(matchScoreDto.getSecondPlayerGames()).isEqualTo(0),

                () -> assertThat(matchScoreDto.getFirstPlayerPoints()).isEqualTo(4),
                () -> assertThat(matchScoreDto.getSecondPlayerPoints()).isEqualTo(3)
        );
    }

    @Test
    void endGameIfPlayerWinsPointAt40And0() {
        matchScoreDto.setFirstPlayerPoints(3);
        matchScoreDto.setSecondPlayerPoints(0);

        matchScoreCalculationService.countScore(matchScoreDto.getFirstPlayer().getId());

        assertAll(
                () -> assertThat(matchScoreDto.getFirstPlayerGames()).isEqualTo(1),
                () -> assertThat(matchScoreDto.getSecondPlayerGames()).isEqualTo(0),

                () -> assertThat(matchScoreDto.getFirstPlayerPoints()).isEqualTo(0),
                () -> assertThat(matchScoreDto.getSecondPlayerPoints()).isEqualTo(0)
        );
    }

    @Test
    void startTieBreakWhenGamesAre6And6() {
        matchScoreDto.setFirstPlayerGames(6);
        matchScoreDto.setSecondPlayerGames(6);

        matchScoreCalculationService.countScore(matchScoreDto.getFirstPlayer().getId());

        assertAll(
                () -> assertThat(matchScoreDto.getFirstPlayerSets()).isEqualTo(0),
                () -> assertThat(matchScoreDto.getSecondPlayerSets()).isEqualTo(0)
        );
    }

    @Test
    void endTieBreakIfPlayerWinsPointAt7And0() {
        matchScoreDto.setFirstPlayerGames(6);
        matchScoreDto.setSecondPlayerGames(6);

        matchScoreDto.setFirstPlayerPoints(7);
        matchScoreDto.setSecondPlayerPoints(0);

        matchScoreCalculationService.countScore(matchScoreDto.getFirstPlayer().getId());

        assertAll(
                () -> assertThat(matchScoreDto.getFirstPlayerSets()).isEqualTo(1),
                () -> assertThat(matchScoreDto.getSecondPlayerSets()).isEqualTo(0),

                () -> assertThat(matchScoreDto.getFirstPlayerGames()).isEqualTo(0),
                () -> assertThat(matchScoreDto.getSecondPlayerGames()).isEqualTo(0),

                () -> assertThat(matchScoreDto.getFirstPlayerPoints()).isEqualTo(0),
                () -> assertThat(matchScoreDto.getSecondPlayerPoints()).isEqualTo(0)
        );
    }

    @Test
    void endTieBreakIfPlayerWinsPointAt9And7() {
        matchScoreDto.setFirstPlayerGames(6);
        matchScoreDto.setSecondPlayerGames(6);

        matchScoreDto.setFirstPlayerPoints(9);
        matchScoreDto.setSecondPlayerPoints(7);

        matchScoreCalculationService.countScore(matchScoreDto.getFirstPlayer().getId());

        assertAll(
                () -> assertThat(matchScoreDto.getFirstPlayerSets()).isEqualTo(1),
                () -> assertThat(matchScoreDto.getSecondPlayerSets()).isEqualTo(0),

                () -> assertThat(matchScoreDto.getFirstPlayerGames()).isEqualTo(0),
                () -> assertThat(matchScoreDto.getSecondPlayerGames()).isEqualTo(0),

                () -> assertThat(matchScoreDto.getFirstPlayerPoints()).isEqualTo(0),
                () -> assertThat(matchScoreDto.getSecondPlayerPoints()).isEqualTo(0)
        );
    }

    @Test
    void dontEndTieBreakIfPlayerWinsPointAt6And6() {
        matchScoreDto.setFirstPlayerGames(6);
        matchScoreDto.setSecondPlayerGames(6);

        matchScoreDto.setFirstPlayerPoints(6);
        matchScoreDto.setSecondPlayerPoints(6);

        matchScoreCalculationService.countScore(matchScoreDto.getFirstPlayer().getId());

        assertAll(
                () -> assertThat(matchScoreDto.getFirstPlayerGames()).isEqualTo(6),
                () -> assertThat(matchScoreDto.getSecondPlayerGames()).isEqualTo(6),

                () -> assertThat(matchScoreDto.getFirstPlayerPoints()).isEqualTo(7),
                () -> assertThat(matchScoreDto.getSecondPlayerPoints()).isEqualTo(6)
        );
    }

    @Test
    void endSetIfPlayerWinsGameAt6And4() {
        matchScoreDto.setFirstPlayerGames(6);
        matchScoreDto.setSecondPlayerGames(4);

        matchScoreCalculationService.countScore(matchScoreDto.getFirstPlayer().getId());

        assertAll(
                () -> assertThat(matchScoreDto.getFirstPlayerSets()).isEqualTo(1),
                () -> assertThat(matchScoreDto.getSecondPlayerSets()).isEqualTo(0),

                () -> assertThat(matchScoreDto.getFirstPlayerGames()).isEqualTo(0),
                () -> assertThat(matchScoreDto.getSecondPlayerGames()).isEqualTo(0)
        );
    }
}
