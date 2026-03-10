package com.erp.dao;

import com.erp.model.Facture;
import com.erp.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class FactureDAO extends AbstractDAO<Facture> {

    public FactureDAO() {
        super(Facture.class);
    }

    // Nouvelle méthode pour filtrer par client
    public List<Facture> findByClientId(Long clientId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT f FROM Facture f WHERE f.commande.client.id = :clientId", Facture.class)
                    .setParameter("clientId", clientId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}