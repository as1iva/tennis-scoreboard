package org.as1iva.util;

import lombok.experimental.UtilityClass;
import org.as1iva.dto.MatchScoreDto;
import org.as1iva.exception.MatchNotFoundException;
import org.as1iva.exception.PlayerNotFoundException;
import org.as1iva.exception.InvalidDataException;
import org.as1iva.exception.PageNotFoundException;
import org.as1iva.service.OngoingMatchesService;

import java.util.UUID;
import java.util.regex.Pattern;

@UtilityClass
public class ParameterValidator {

    OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();

    private static final String PAGE_NOT_FOUND_MESSAGE = "Oops... this page does not exist";

    public static void checkNameEquality(String firstPlayerName, String secondPlayerName) {
        if (firstPlayerName.equals(secondPlayerName)) {
            throw new InvalidDataException("Players must be different");
        }
    }

    public static void checkName(String playerName) {
        Pattern pattern = Pattern.compile("[A-Za-z]\\. [A-Za-z]+");

        if (playerName == null || playerName.isEmpty()) {
            throw new InvalidDataException("Player cannot have empty name");
        }

        if (!pattern.matcher(playerName).matches()) {
            throw new InvalidDataException("Player's name must be in English and follow this format: N. Surname");
        }

        if (playerName.length() > 20) {
            throw new InvalidDataException("Player's name too long (max. 20)");
        }
    }

    public static int checkPageParameter(String pageParam) {
        Pattern pattern = Pattern.compile("^[1-9]\\d*$");
        int page;

        if (pageParam == null || pageParam.isEmpty()) {
            page = 1;
        } else if (!pattern.matcher(pageParam).matches()) {
            throw new PageNotFoundException(PAGE_NOT_FOUND_MESSAGE);
        } else {
            page = Integer.parseInt(pageParam);
        }

        return page;
    }

    public static void checkPageParameter(int page, long totalPage) {
        if (totalPage > 0 && page > totalPage) {
            throw new PageNotFoundException(PAGE_NOT_FOUND_MESSAGE);
        } else {
            throw new PlayerNotFoundException("No players was found");
        }
    }

    public static void checkMatchId(UUID matchId) {
        MatchScoreDto matchScoreDto = ongoingMatchesService.getMatchScore(matchId);

        if (matchScoreDto == null) {
            throw new MatchNotFoundException("Match not found");
        }
    }
}
