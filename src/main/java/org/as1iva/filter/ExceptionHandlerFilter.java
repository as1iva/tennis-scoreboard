package org.as1iva.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.as1iva.exception.InvalidDataException;

import java.io.IOException;

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
    }
}
