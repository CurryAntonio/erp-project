package com.erp.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String loginURI = req.getContextPath() + "/views/login.jsp";

        String loginServlet = req.getContextPath() + "/login";

        HttpSession session = req.getSession(false);

        boolean loggedIn =
                session != null && session.getAttribute("user") != null;

        boolean loginRequest =
                req.getRequestURI().equals(loginURI) || req.getRequestURI().equals(loginServlet);

        boolean resourceRequest =
                req.getRequestURI().contains("/assets/");

        if (loggedIn || loginRequest || resourceRequest) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(loginURI);
        }
    }
}
