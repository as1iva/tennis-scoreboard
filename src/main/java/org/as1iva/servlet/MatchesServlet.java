package org.as1iva.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.as1iva.dto.MatchResponseDto;
import org.as1iva.service.FinishedMatchesPersistenceService;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MatchResponseDto> matches = finishedMatchesPersistenceService.getAllMatches();

        req.setAttribute("matches", matches);
        req.getRequestDispatcher("/jsp/matches.jsp").forward(req, resp);
    }
}
