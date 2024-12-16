package org.as1iva.service;

import lombok.AllArgsConstructor;
import org.as1iva.dto.MatchScoreDto;

@AllArgsConstructor
public class MatchScoreCalculationService {

    private final MatchScoreDto matchScoreDto;

    public void countScore(Long id) {
        countSets(id);
    }

    private void countPoints(Long id) {

        if (id == 0) {
            matchScoreDto.setFirstPlayerPoints(matchScoreDto.getFirstPlayerPoints() + 1);

            if (matchScoreDto.getFirstPlayerPoints() == 4 && matchScoreDto.getSecondPlayerPoints() <= 2) {
                matchScoreDto.setFirstPlayerGames(matchScoreDto.getFirstPlayerGames() + 1);

                resetPoints();

            } else if (matchScoreDto.getFirstPlayerPoints() == 5 && matchScoreDto.getSecondPlayerPoints() == 3) {
                matchScoreDto.setFirstPlayerGames(matchScoreDto.getFirstPlayerGames() + 1);

                resetPoints();
            }
        }

        if (id == 1) {
            matchScoreDto.setSecondPlayerPoints(matchScoreDto.getSecondPlayerPoints() + 1);

            if (matchScoreDto.getSecondPlayerPoints() == 4 && matchScoreDto.getFirstPlayerPoints() <= 2) {
                matchScoreDto.setSecondPlayerGames(matchScoreDto.getSecondPlayerGames() + 1);

                resetPoints();
            } else if (matchScoreDto.getSecondPlayerPoints() == 5 && matchScoreDto.getFirstPlayerPoints() == 3) {
                matchScoreDto.setSecondPlayerGames(matchScoreDto.getSecondPlayerGames() + 1);

                resetPoints();
            }
        }

        if (matchScoreDto.getFirstPlayerPoints() == 4 && matchScoreDto.getSecondPlayerPoints() == 4) {
            matchScoreDto.setFirstPlayerPoints(3);
            matchScoreDto.setSecondPlayerPoints(3);
        }
    }

    private void countGames(Long id) {
        if (matchScoreDto.getFirstPlayerGames() == 6 && matchScoreDto.getSecondPlayerGames() == 6) {
            if (id == 0) {
                matchScoreDto.setFirstPlayerPoints(matchScoreDto.getFirstPlayerPoints() + 1);

                if (matchScoreDto.getFirstPlayerPoints() >= 7 && (matchScoreDto.getSecondPlayerPoints() <= matchScoreDto.getFirstPlayerPoints() - 2)) {
                    matchScoreDto.setFirstPlayerSets(matchScoreDto.getFirstPlayerSets() + 1);

                    resetGames();
                    resetPoints();
                }
            }

            if (id == 1) {
                matchScoreDto.setSecondPlayerPoints(matchScoreDto.getSecondPlayerPoints() + 1);

                if (matchScoreDto.getSecondPlayerPoints() >= 7 && (matchScoreDto.getFirstPlayerPoints() <= matchScoreDto.getSecondPlayerPoints() - 2)) {
                    matchScoreDto.setSecondPlayerSets(matchScoreDto.getSecondPlayerSets() + 1);

                    resetGames();
                    resetPoints();
                }
            }
            return;
        }

        if (id == 0) {
            countPoints(id);

            if (matchScoreDto.getFirstPlayerGames() == 6 && matchScoreDto.getSecondPlayerGames() <= 4) {
                matchScoreDto.setFirstPlayerSets(matchScoreDto.getFirstPlayerSets() + 1);

                resetGames();
            } else if (matchScoreDto.getFirstPlayerGames() == 7 && matchScoreDto.getSecondPlayerGames() == 5) {
                matchScoreDto.setFirstPlayerSets(matchScoreDto.getFirstPlayerSets() + 1);

                resetGames();
            }
        }

        if (id == 1) {
            countPoints(id);

            if (matchScoreDto.getSecondPlayerGames() == 6 && matchScoreDto.getFirstPlayerGames() <= 4) {
                matchScoreDto.setSecondPlayerSets(matchScoreDto.getSecondPlayerSets() + 1);

                resetGames();
            } else if (matchScoreDto.getSecondPlayerGames() == 7 && matchScoreDto.getFirstPlayerGames() == 5) {
                matchScoreDto.setSecondPlayerSets(matchScoreDto.getSecondPlayerSets() + 1);

                resetGames();
            }
        }
    }

    private void countSets(Long id) {
        countGames(id);

        if (id == 0) {
            if (matchScoreDto.getFirstPlayerSets() == 2) {
                System.out.println("Игрок выиграл" + matchScoreDto.getFirstPlayer().getName());
            }
        }

        if (id == 1) {
            if (matchScoreDto.getSecondPlayerSets() == 2) {
                System.out.println("Игрок выиграл" + matchScoreDto.getSecondPlayer().getName());
            }
        }
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
