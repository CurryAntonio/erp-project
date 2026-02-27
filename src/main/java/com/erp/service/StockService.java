package com.erp.service;

import com.erp.model.Produit;
import com.erp.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class StockService {

    public void diminuerStock(Long produitId, int quantite) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Produit produit = em.find(Produit.class, produitId);

        if (produit == null)
            throw new RuntimeException("Produit introuvable");
        if (produit.getQuantiteStock() < quantite)
            throw new RuntimeException("Stock insuffisant");

        produit.setQuantiteStock(
                produit.getQuantiteStock() - quantite
        );

        em.getTransaction().commit();
        em.close();
    }

    public void augmenterStock(Long produitId, int quantite) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Produit produit = em.find(Produit.class, produitId);

        if (produit == null)
            throw new RuntimeException("Produit introuvable");

        produit.setQuantiteStock(
                produit.getQuantiteStock() + quantite
        );

        em.getTransaction().commit();
        em.close();
    }
}
