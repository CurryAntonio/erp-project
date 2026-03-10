package com.erp.web;

import com.erp.enums.RoleUtilisateur;
import com.erp.model.Utilisateur;
import com.erp.dao.UtilisateurDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/complete-profile")
public class CompleteProfileServlet extends HttpServlet {
    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Utilisateur pendingUser = (Utilisateur) session.getAttribute("pendingUser");

        if (pendingUser == null) {
            resp.sendRedirect(req.getContextPath() + "/views/login.jsp");
            return;
        }

        // Récupération du rôle choisi dans le formulaire
        String roleStr = req.getParameter("role");
        try {
            RoleUtilisateur role = RoleUtilisateur.valueOf(roleStr);
            pendingUser.setRole(role);

            // Mise à jour de l'utilisateur en base
            utilisateurDAO.update(pendingUser);

            // Nettoyage de la session et redirection finale
            session.removeAttribute("pendingUser");
            resp.sendRedirect(req.getContextPath() + "/views/login.jsp?reg=success");

        } catch (IllegalArgumentException e) {
            resp.sendRedirect(req.getContextPath() + "/views/choose-profile.jsp?error=invalid_role");
        }
    }
}