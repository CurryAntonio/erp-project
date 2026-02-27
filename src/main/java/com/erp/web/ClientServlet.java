package com.erp.web;

import com.erp.model.Client;
import com.erp.service.ClientService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet("/clients")
public class ClientServlet extends HttpServlet {

    private ClientService clientService = new ClientService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        req.setAttribute("clients", clientService.listerClients());
        req.getRequestDispatcher("/views/clients.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Client c = new Client();
        c.setNom(req.getParameter("nom"));
        c.setPrenom(req.getParameter("prenom"));
        c.setEmail(req.getParameter("email"));
        c.setAdresse(req.getParameter("adresse"));

        clientService.creerClient(c);

        resp.sendRedirect("clients");
    }
}
