package com.erp.service;

import com.erp.dao.ProduitDAO;
import com.erp.model.Produit;

import java.util.List;

public class ProduitService {

    private ProduitDAO produitDAO = new ProduitDAO();

    public void creerProduit(Produit produit) {
        produitDAO.save(produit);
    }

    public Produit trouverProduit(Long id) {
        return produitDAO.find(id);
    }

    public List<Produit> listerProduits() {
        return produitDAO.findAll();
    }

    public Produit modifierProduit(Produit produit) {
        return produitDAO.update(produit);
    }

    public void supprimerProduit(Long id) {
        produitDAO.delete(id);
    }
}
