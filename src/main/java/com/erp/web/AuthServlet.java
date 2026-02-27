package com.erp.web;

import com.erp.model.Utilisateur;
import com.erp.service.AuthService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {

    private AuthService authService = new AuthService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Utilisateur user = authService.login(login, password);

        if (user == null) {
            resp.sendRedirect("views/login.jsp?error=1");
            return;
        }

        req.getSession().setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + "/dashboard");
    }
}
