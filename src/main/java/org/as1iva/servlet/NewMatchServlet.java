package org.as1iva.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.as1iva.dto.MatchScoreDto;
import org.as1iva.entity.Player;
import org.as1iva.service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstPlayerName = req.getParameter("playerOne");
        String secondPlayerName = req.getParameter("playerTwo");

        Player playerOne = Player.builder()
                        .id(0L)
                        .name(firstPlayerName)
                        .build();

        Player playerTwo = Player.builder()
                        .id(1L)
                        .name(secondPlayerName)
                        .build();

        MatchScoreDto match = MatchScoreDto.builder()
                .firstPlayer(playerOne)
                .secondPlayer(playerTwo)
                .build();

        UUID uuid = ongoingMatchesService.setMatchScore(match);

        resp.sendRedirect("/match-score?uuid=" + uuid);
    }
}
