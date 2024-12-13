package org.as1iva.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.as1iva.dto.MatchScoreDto;
import org.as1iva.service.MatchScoreCalculationService;
import org.as1iva.service.OutgoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    private final OutgoingMatchesService outgoingMatchesService = new OutgoingMatchesService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID matchId = UUID.fromString(req.getParameter("uuid"));
        Long playerId = Long.parseLong(req.getParameter("playerId"));

        MatchScoreDto matchScoreDto = outgoingMatchesService.getMatchScore(matchId);

        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(matchScoreDto);

        System.out.println("Выиграл игрок " + playerId);

        matchScoreCalculationService.countScore(playerId);

        req.setAttribute("matchScoreDto", matchScoreDto);
        req.setAttribute("uuid", matchId);

        req.getRequestDispatcher("/jsp/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID matchId = UUID.fromString(req.getParameter("uuid"));

        MatchScoreDto matchScoreDto = outgoingMatchesService.getMatchScore(matchId);

        req.setAttribute("matchScoreDto", matchScoreDto);
        req.setAttribute("uuid", matchId);

        req.getRequestDispatcher("/jsp/match-score.jsp").forward(req, resp);
    }
}
