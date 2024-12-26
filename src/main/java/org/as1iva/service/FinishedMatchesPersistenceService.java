package org.as1iva.service;

import lombok.Getter;
import org.as1iva.dto.MatchScoreDto;
import org.as1iva.entity.Match;
import org.as1iva.entity.Player;
import org.as1iva.repository.MatchRepository;
import org.as1iva.repository.PlayerRepository;

public class FinishedMatchesPersistenceService {

    private final MatchRepository matchRepository = MatchRepository.getINSTANCE();

    private final PlayerRepository playerRepository = PlayerRepository.getINSTANCE();

    @Getter
    private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();

    private FinishedMatchesPersistenceService() {
    }

    public void saveMatch(MatchScoreDto matchScoreDto, Player winner) {
        Player playerOne = savePlayer(matchScoreDto.getFirstPlayer().getName());
        Player playerTwo = savePlayer(matchScoreDto.getSecondPlayer().getName());

        if (winner.getId().equals(matchScoreDto.getFirstPlayer().getId())) {
            winner = playerOne;
        } else {
            winner = playerTwo;
        }

        Match match = Match.builder()
                .firstPlayer(playerOne)
                .secondPlayer(playerTwo)
                .winner(winner)
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
