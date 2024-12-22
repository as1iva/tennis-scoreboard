package org.as1iva.service;

import org.as1iva.dto.MatchResponseDto;
import org.as1iva.dto.MatchScoreDto;
import org.as1iva.dto.PlayerResponseDto;
import org.as1iva.entity.Match;
import org.as1iva.entity.Player;
import org.as1iva.repository.MatchRepository;
import org.as1iva.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

public class FinishedMatchesPersistenceService {

    private final MatchRepository matchRepository = new MatchRepository();
    private final PlayerRepository playerRepository = new PlayerRepository();

    public List<MatchResponseDto> getAllMatches() {
        List<MatchResponseDto> matchResponseDtoList = new ArrayList<>();

        List<Match> matches = matchRepository.findAll();

        for(Match match : matches) {
            Player playerOne = playerRepository.findById(match.getFirstPlayer().getId()).orElseThrow();
            Player playerTwo = playerRepository.findById(match.getSecondPlayer().getId()).orElseThrow();
            Player winner = playerRepository.findById(match.getWinner().getId()).orElseThrow();

            matchResponseDtoList.add(
                    MatchResponseDto.builder()
                            .firstPlayer(PlayerResponseDto.builder()
                                    .name(playerOne.getName())
                                    .build())
                            .secondPlayer(PlayerResponseDto.builder()
                                    .name(playerTwo.getName())
                                    .build())
                            .winner(PlayerResponseDto.builder()
                                    .name(winner.getName())
                                    .build())
                            .build()
            );
        }
        return matchResponseDtoList;
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
