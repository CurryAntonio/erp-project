package com.erp.web;

import com.erp.model.*;
import com.erp.enums.RoleUtilisateur;
import com.erp.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/commande")
public class CommandeServlet extends HttpServlet {

    private CommandeService commandeService = new CommandeService();
    private ClientService clientService = new ClientService();
    private ProduitService produitService = new ProduitService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("user");

        // --- Logique de filtrage ---
        List<Commande> commandes;
        if (user.getRole() == RoleUtilisateur.CLIENT) {
            // Un client ne voit que ses propres commandes
            commandes = commandeService.findByClient(user.getClient().getId());
        } else {
            // Admin et Gestionnaire voient tout
            commandes = commandeService.listerCommandes();
        }

        req.setAttribute("commandes", commandes);
        // Les données de création ne sont envoyées que pour le personnel
        req.setAttribute("clients", clientService.listerClients());
        req.setAttribute("produits", produitService.listerProduits());

        req.getRequestDispatcher("/views/commande.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String action = req.getParameter("action");
        try {
            if ("valider".equals(action)) {
                Long id = Long.parseLong(req.getParameter("id"));
                commandeService.validerCommande(id);
                resp.sendRedirect(req.getContextPath() + "/commande?success=validee");
                return;
            }

            Long clientId = Long.parseLong(req.getParameter("clientId"));
            Long produitId = Long.parseLong(req.getParameter("produitId"));
            int quantite = Integer.parseInt(req.getParameter("quantite"));

            Client client = clientService.trouverClient(clientId);
            Map<Long, Integer> produitsMap = new HashMap<>();
            produitsMap.put(produitId, quantite);

            commandeService.creerCommande(client, produitsMap);
            resp.sendRedirect(req.getContextPath() + "/commande?success=1");
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/commande?error=process");
        }
    }
}