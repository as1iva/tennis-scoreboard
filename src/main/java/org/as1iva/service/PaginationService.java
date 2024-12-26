package org.as1iva.service;

import org.as1iva.dto.MatchResponseDto;
import org.as1iva.dto.PlayerResponseDto;
import org.as1iva.entity.Match;
import org.as1iva.entity.Player;
import org.as1iva.repository.MatchRepository;
import org.as1iva.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

public class PaginationService {

    private final PlayerRepository playerRepository = new PlayerRepository();
    private final MatchRepository matchRepository = MatchRepository.getINSTANCE();

    private static final int PAGE_SIZE = 5;

    public List<MatchResponseDto> getAllMatchesWithPagination(int page) {
        List<MatchResponseDto> matchResponseDtoList = new ArrayList<>();

        List<Match> matches = matchRepository.findAllWithPagination(page, PAGE_SIZE);

        for (Match match : matches) {
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

    public List<MatchResponseDto> getMatchesWithPaginationByPlayerName(int page, String playerName) {
        List<MatchResponseDto> matchResponseDtoList = new ArrayList<>();

        List<Match> matches = matchRepository.findByNameWithPagination(page, PAGE_SIZE, playerName);

        for (Match match : matches) {
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

    public long getTotalPages() {
        long totalRecords = matchRepository.count();
        return (long) Math.ceil((double) totalRecords / PAGE_SIZE);
    }

    public long getTotalPagesByName(String playerName) {
        long totalRecords = matchRepository.countByName(playerName);
        return (long) Math.ceil((double) totalRecords / PAGE_SIZE);
    }
}
