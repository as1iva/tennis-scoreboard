package org.as1iva.service;

import lombok.Getter;
import org.as1iva.dto.MatchResponseDto;
import org.as1iva.dto.PlayerResponseDto;
import org.as1iva.entity.Match;
import org.as1iva.entity.Player;
import org.as1iva.exception.PlayerNotFoundException;
import org.as1iva.repository.MatchRepository;
import org.as1iva.repository.PlayerRepository;
import org.as1iva.util.MapperUtil;

import java.util.ArrayList;
import java.util.List;

public class PaginationService {

    private final MatchRepository matchRepository = MatchRepository.getINSTANCE();

    private final PlayerRepository playerRepository = PlayerRepository.getINSTANCE();

    private static final int PAGE_SIZE = 5;

    private static final String PLAYER_NOT_FOUND = "Players cannot be found";

    @Getter
    private static final PaginationService INSTANCE = new PaginationService();

    private PaginationService() {
    }

    public List<MatchResponseDto> getAllMatchesWithPagination(int page) {
        List<Match> matches = matchRepository.findAllWithPagination(page, PAGE_SIZE);
        return getMatchResponseDtoList(matches);
    }

    public List<MatchResponseDto> getMatchesWithPaginationByPlayerName(int page, String playerName) {
        List<Match> matches = matchRepository.findByNameWithPagination(page, PAGE_SIZE, playerName);
        return getMatchResponseDtoList(matches);
    }

    private List<MatchResponseDto> getMatchResponseDtoList(List<Match> matches) {
        List<MatchResponseDto> matchResponseDtoList = new ArrayList<>();

        for (Match match : matches) {
            Player playerOne = playerRepository.findById(match.getFirstPlayer().getId())
                    .orElseThrow(() -> new PlayerNotFoundException(PLAYER_NOT_FOUND));

            Player playerTwo = playerRepository.findById(match.getSecondPlayer().getId())
                    .orElseThrow(() -> new PlayerNotFoundException(PLAYER_NOT_FOUND));

            Player winner = playerRepository.findById(match.getWinner().getId())
                    .orElseThrow(() -> new PlayerNotFoundException(PLAYER_NOT_FOUND));

            PlayerResponseDto playerOneResponseDto = MapperUtil.INSTANCE.toDto(playerOne);
            PlayerResponseDto playerTwoResponseDto = MapperUtil.INSTANCE.toDto(playerTwo);
            PlayerResponseDto playerWinnerResponseDto = MapperUtil.INSTANCE.toDto(winner);

            matchResponseDtoList.add(
                    MatchResponseDto.builder()
                            .firstPlayer(playerOneResponseDto)
                            .secondPlayer(playerTwoResponseDto)
                            .winner(playerWinnerResponseDto)
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
