package org.as1iva.service;

import org.as1iva.dto.MatchScoreDto;
import org.as1iva.entity.Match;
import org.as1iva.entity.Player;
import org.as1iva.repository.MatchRepository;
import org.as1iva.repository.PlayerRepository;

public class FinishedMatchesPersistenceService {

    private final MatchRepository matchRepository = new MatchRepository();
    private final PlayerRepository playerRepository = new PlayerRepository();

    public void saveMatch(MatchScoreDto matchScoreDto, Player winner) {
        Player playerOne = savePlayer(matchScoreDto.getFirstPlayer().getName());
        Player playerTwo = savePlayer(matchScoreDto.getSecondPlayer().getName());

        if (winner.getId().equals(matchScoreDto.getFirstPlayer().getId())) {
            winner = playerOne;
        } else {
            winner = playerTwo;
        }

        Match match = Match.builder()
                .firstPlayer(playerOne.getId())
                .secondPlayer(playerTwo.getId())
                .winner(winner.getId())
                .build();

        matchRepository.save(match);
    }

    private Player savePlayer(String playerName) {
        return playerRepository.findByName(playerName).orElseGet(() -> playerRepository.save(
                Player.builder()
                        .name(playerName)
                        .build()
                )
        );
    }
}