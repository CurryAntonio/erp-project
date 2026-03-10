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

        String path = req.getRequestURI().substring(req.getContextPath().length());
        HttpSession session = req.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        // Ajout de /register et register_form.jsp aux chemins publics
        boolean isPublicPath = path.equals("/login") ||
                path.equals("/views/login.jsp") ||
                path.equals("/register") ||
                path.equals("/views/register_form.jsp");
        boolean isResource = path.contains("/assets/");

        if (loggedIn || isPublicPath || isResource) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + "/views/login.jsp");
        }
    }
}