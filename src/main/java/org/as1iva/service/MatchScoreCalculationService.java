package org.as1iva.service;

import lombok.AllArgsConstructor;
import org.as1iva.dto.MatchScoreDto;

@AllArgsConstructor
public class MatchScoreCalculationService {

    private final MatchScoreDto matchScoreDto;

    public void countScore(Long id) {
        countSets(id);
    }
    public boolean isMatchOver() {
        return matchScoreDto.getFirstPlayerSets() == 2 || matchScoreDto.getSecondPlayerSets() == 2;
    }

    private void countGames(Long id) {

        if (id == 0) {
            matchScoreDto.setFirstPlayerPoints(matchScoreDto.getFirstPlayerPoints() + 1);

            if (hasPlayerWonGame(matchScoreDto.getFirstPlayerPoints(), matchScoreDto.getSecondPlayerPoints())) {
                matchScoreDto.setFirstPlayerGames(matchScoreDto.getFirstPlayerGames() + 1);

                resetPoints();
            }
        }

        if (id == 1) {
            matchScoreDto.setSecondPlayerPoints(matchScoreDto.getSecondPlayerPoints() + 1);

            if (hasPlayerWonGame(matchScoreDto.getSecondPlayerPoints(), matchScoreDto.getFirstPlayerPoints())) {
                matchScoreDto.setSecondPlayerGames(matchScoreDto.getSecondPlayerGames() + 1);

                resetPoints();
            }
        }

        if (matchScoreDto.getFirstPlayerPoints() == 4 && matchScoreDto.getSecondPlayerPoints() == 4) {
            matchScoreDto.setFirstPlayerPoints(3);
            matchScoreDto.setSecondPlayerPoints(3);
        }
    }

    private void countSets(Long id) {
        if (isTieBreak()) {
            countTieBreak(id);
            return;
        }

        if (id == 0) {
            countGames(id);

            if (hasPlayerWonSet(matchScoreDto.getFirstPlayerGames(), matchScoreDto.getSecondPlayerGames())) {
                matchScoreDto.setFirstPlayerSets(matchScoreDto.getFirstPlayerSets() + 1);

                resetGames();
            }
        }

        if (id == 1) {
            countGames(id);

            if (hasPlayerWonSet(matchScoreDto.getSecondPlayerGames(), matchScoreDto.getFirstPlayerGames())) {
                matchScoreDto.setSecondPlayerSets(matchScoreDto.getSecondPlayerSets() + 1);

                resetGames();
            }
        }
    }

    private void countTieBreak(Long id) {
        if (id == 0) {
            matchScoreDto.setFirstPlayerPoints(matchScoreDto.getFirstPlayerPoints() + 1);

            if (hasPlayerWonTieBreak(matchScoreDto.getFirstPlayerPoints(), matchScoreDto.getSecondPlayerPoints())) {
                matchScoreDto.setFirstPlayerSets(matchScoreDto.getFirstPlayerSets() + 1);

                resetGames();
                resetPoints();
            }
        }

        if (id == 1) {
            matchScoreDto.setSecondPlayerPoints(matchScoreDto.getSecondPlayerPoints() + 1);

            if (hasPlayerWonTieBreak(matchScoreDto.getSecondPlayerPoints(), matchScoreDto.getFirstPlayerPoints())) {
                matchScoreDto.setSecondPlayerSets(matchScoreDto.getSecondPlayerSets() + 1);

                resetGames();
                resetPoints();
            }
        }
    }

    private boolean hasPlayerWonGame(int playerPoints, int opponentPoints) {
        return (playerPoints == 4 && opponentPoints <= 2) || (playerPoints == 5 && opponentPoints == 3);
    }

    private boolean isTieBreak() {
        return matchScoreDto.getFirstPlayerGames() == 6 && matchScoreDto.getSecondPlayerGames() == 6;
    }

    private boolean hasPlayerWonTieBreak(int playerPoints, int opponentPoints) {
        return playerPoints >= 7 && (opponentPoints <= playerPoints - 2);
    }

    private boolean hasPlayerWonSet(int playerGames, int opponentGames) {
        return (playerGames == 6 && opponentGames <= 4) || (playerGames == 7 && opponentGames == 5);
    }

    private void resetPoints() {
        matchScoreDto.setFirstPlayerPoints(0);
        matchScoreDto.setSecondPlayerPoints(0);
    }

    private void resetGames() {
        matchScoreDto.setFirstPlayerGames(0);
        matchScoreDto.setSecondPlayerGames(0);
    }
}
