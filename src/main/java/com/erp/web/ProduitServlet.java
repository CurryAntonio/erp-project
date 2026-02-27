package com.erp.web;

import com.erp.model.Produit;
import com.erp.service.ProduitService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {

    private ProduitService produitService = new ProduitService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        req.setAttribute("produits", produitService.listerProduits());
        req.getRequestDispatcher("views/produits.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {

        Produit p = new Produit();
        p.setLibelle(req.getParameter("libelle"));
        p.setReference(req.getParameter("reference"));
        p.setPrixUnitaire(new BigDecimal(req.getParameter("prix")));
        p.setQuantiteStock(Integer.parseInt(req.getParameter("stock")));

        produitService.creerProduit(p);

        resp.sendRedirect("produits");
    }
}
