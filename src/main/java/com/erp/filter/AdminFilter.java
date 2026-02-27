package com.erp.filter;

import com.erp.enums.RoleUtilisateur;
import com.erp.model.Utilisateur;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect("views/login.jsp");
            return;
        }

        Utilisateur user = (Utilisateur) session.getAttribute("user");

        if (user.getRole() != RoleUtilisateur.ADMIN) {
            resp.sendRedirect("dashboard");
            return;
        }

        chain.doFilter(request, response);
    }
}
