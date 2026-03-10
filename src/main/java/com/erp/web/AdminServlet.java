package com.erp.web;

import com.erp.dao.UtilisateurDAO;
import com.erp.enums.RoleUtilisateur;
import com.erp.model.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/users")
public class AdminServlet extends HttpServlet {
    private UtilisateurDAO userDAO = new UtilisateurDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("usersList", userDAO.findAll());
        req.getRequestDispatcher("/views/admin_users.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("userId"));
        RoleUtilisateur role = RoleUtilisateur.valueOf(req.getParameter("newRole"));

        Utilisateur u = userDAO.find(id);
        u.setRole(role);
        userDAO.update(u);

        resp.sendRedirect(req.getContextPath() + "/admin/users?success=1");
    }
}