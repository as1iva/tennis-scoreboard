package org.as1iva.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.as1iva.dto.MatchScoreDto;
import org.as1iva.entity.Player;
import org.as1iva.service.OngoingMatchesService;
import org.as1iva.util.ParameterValidator;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private static final Long PLACEHOLDER_FIRST_PLAYER_ID = 0L;

    private static final Long PLACEHOLDER_SECOND_PLAYER_ID = 1L;

    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstPlayerName = req.getParameter("playerOne").trim();
        String secondPlayerName = req.getParameter("playerTwo").trim();

        ParameterValidator.checkName(firstPlayerName);
        ParameterValidator.checkName(secondPlayerName);
        ParameterValidator.checkNameEquality(firstPlayerName, secondPlayerName);

        firstPlayerName = formatName(firstPlayerName);
        secondPlayerName = formatName(secondPlayerName);

        Player playerOne = Player.builder()
                        .id(PLACEHOLDER_FIRST_PLAYER_ID)
                        .name(firstPlayerName)
                        .build();

        Player playerTwo = Player.builder()
                        .id(PLACEHOLDER_SECOND_PLAYER_ID)
                        .name(secondPlayerName)
                        .build();

        MatchScoreDto match = MatchScoreDto.builder()
                .firstPlayer(playerOne)
                .secondPlayer(playerTwo)
                .build();

        UUID uuid = ongoingMatchesService.setMatchScore(match);

        resp.sendRedirect("/match-score?uuid=" + uuid);
    }

    private String formatName(String playerName) {
        String[] parts = playerName.split("\\. ");
        String name = parts[0].toUpperCase();
        String surname = parts[1].substring(0, 1).toUpperCase() + parts[1].substring(1).toLowerCase();

        return name + ". " + surname;
    }
}
