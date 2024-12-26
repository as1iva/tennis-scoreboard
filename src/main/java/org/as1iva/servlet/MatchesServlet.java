package org.as1iva.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.as1iva.dto.MatchResponseDto;
import org.as1iva.service.PaginationService;
import org.as1iva.util.ParameterValidator;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    private final PaginationService paginationService = new PaginationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageParam = req.getParameter("page");
        String playerName = req.getParameter("filter_by_name");

        int page = ParameterValidator.checkPageParameter(pageParam);

        List<MatchResponseDto> matches;
        long totalPages;

        if (playerName != null) {
            matches = paginationService.getMatchesWithPaginationByPlayerName(page, playerName);
            totalPages = paginationService.getTotalPagesByName(playerName);
        } else {
            matches = paginationService.getAllMatchesWithPagination(page);
            totalPages = paginationService.getTotalPages();
        }

        req.setAttribute("totalPages", totalPages);
        req.setAttribute("matches", matches);
        req.setAttribute("page", page);

        ParameterValidator.checkPageParameter(page, totalPages);

        req.getRequestDispatcher("/jsp/matches.jsp").forward(req, resp);
    }
}
