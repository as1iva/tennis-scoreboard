package org.as1iva.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.as1iva.dto.MatchScoreDto;
import org.as1iva.entity.Player;
import org.as1iva.service.FinishedMatchesPersistenceService;
import org.as1iva.service.MatchScoreCalculationService;
import org.as1iva.service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID matchId = UUID.fromString(req.getParameter("uuid"));
        Long playerId = Long.parseLong(req.getParameter("playerId"));

        MatchScoreDto matchScoreDto = ongoingMatchesService.getMatchScore(matchId);

        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(matchScoreDto);

        matchScoreCalculationService.countScore(playerId);

        if (!matchScoreCalculationService.isMatchOver()) {
            req.setAttribute("matchScoreDto", matchScoreDto);
            req.setAttribute("uuid", matchId);

            req.getRequestDispatcher("/jsp/match-score.jsp").forward(req, resp);
        } else {
            Player winner = matchScoreCalculationService.getWinner();

            finishedMatchesPersistenceService.saveMatch(matchScoreDto, winner);

            ongoingMatchesService.removeMatchScore(matchId);

            resp.sendRedirect("/matches");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID matchId = UUID.fromString(req.getParameter("uuid"));

        MatchScoreDto matchScoreDto = ongoingMatchesService.getMatchScore(matchId);

        req.setAttribute("matchScoreDto", matchScoreDto);
        req.setAttribute("uuid", matchId);

        req.getRequestDispatcher("/jsp/match-score.jsp").forward(req, resp);
    }
}
