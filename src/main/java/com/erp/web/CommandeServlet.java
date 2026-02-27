package com.erp.web;

import com.erp.model.Client;
import com.erp.service.CommandeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/commande")
public class CommandeServlet extends HttpServlet {

    private CommandeService commandeService = new CommandeService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Long clientId = Long.parseLong(req.getParameter("clientId"));
        Long produitId = Long.parseLong(req.getParameter("produitId"));
        int quantite = Integer.parseInt(req.getParameter("quantite"));

        Client client = new Client();
        client.setId(clientId);

        Map<Long, Integer> map = new HashMap<>();
        map.put(produitId, quantite);
    }
}
