package com.erp.web;

import com.erp.model.Client;
import com.erp.model.Utilisateur;
import com.erp.service.ClientService;
import com.erp.dao.UtilisateurDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.List;

@WebServlet("/clients")
public class ClientServlet extends HttpServlet {

    private ClientService clientService = new ClientService();
    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // On récupère tous les comptes utilisateurs pour l'affichage de l'annuaire
        List<Utilisateur> users = utilisateurDAO.findAll();
        req.setAttribute("usersList", users);

        // On garde aussi la liste des clients métiers au cas où vous en auriez besoin
        req.setAttribute("clients", clientService.listerClients());

        req.getRequestDispatcher("/views/clients.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        // Logique pour ajouter un nouveau client (données métier)
        Client c = new Client();
        c.setNom(req.getParameter("nom"));
        c.setPrenom(req.getParameter("prenom"));
        c.setEmail(req.getParameter("email"));
        c.setAdresse(req.getParameter("adresse"));

        clientService.creerClient(c);

        // Redirection vers le servlet pour rafraîchir la liste
        resp.sendRedirect(req.getContextPath() + "/clients");
    }
}