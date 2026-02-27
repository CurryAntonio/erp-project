package com.erp.web;

import com.erp.model.Commande;
import com.erp.service.FactureService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/facture")
public class FactureServlet extends HttpServlet {

    private FactureService factureService = new FactureService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long commandeId = Long.parseLong(req.getParameter("commandeId"));

        Commande commande = new Commande();
        commande.setId(commandeId);

        factureService.genererFacture(commande);

        resp.sendRedirect("dashboard");
    }
}
