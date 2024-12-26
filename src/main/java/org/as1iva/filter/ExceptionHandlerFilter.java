package org.as1iva.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.as1iva.exception.DataNotFoundException;
import org.as1iva.exception.InvalidDataException;
import org.as1iva.exception.PageNotFoundException;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebFilter("/*")
public class ExceptionHandlerFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        try {
            super.doFilter(req, resp, chain);
        }
        catch(InvalidDataException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/new-match.jsp").forward(req, resp);
        }
        catch(PageNotFoundException e) {
            req.setAttribute("errorCode", SC_NOT_FOUND);
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
        catch(DataNotFoundException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/jsp/matches.jsp").forward(req, resp);
        }
    }
}
