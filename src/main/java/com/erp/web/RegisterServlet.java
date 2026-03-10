package com.erp.web;

import com.erp.model.*;
import com.erp.dao.*;
import com.erp.enums.RoleUtilisateur;
import com.erp.util.PasswordUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UtilisateurDAO userDAO = new UtilisateurDAO();
    private ClientDAO clientDAO = new ClientDAO(); // Assurez-vous d'avoir un ClientDAO

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register_form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Client client = new Client();
            client.setNom(req.getParameter("nom"));
            client.setPrenom(req.getParameter("prenom"));
            client.setEmail(req.getParameter("email"));
            client.setAdresse(req.getParameter("adresse"));
            clientDAO.save(client);

            Utilisateur user = new Utilisateur();
            user.setLogin(req.getParameter("login"));
            user.setPassword(PasswordUtil.hash(req.getParameter("password")));
            user.setRole(RoleUtilisateur.valueOf(req.getParameter("role")));
            user.setClient(client);

            userDAO.save(user);
            resp.sendRedirect(req.getContextPath() + "/views/login.jsp?reg=success");
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/register?error=1");
        }
    }
}