package com.erp.service;

import com.erp.model.Commande;
import com.erp.model.Facture;
import com.erp.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FactureService {

    public Facture genererFacture(Commande commande) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Facture facture = new Facture();
        facture.setCommande(commande);
        facture.setDateFacturation(LocalDate.now());

        BigDecimal totalTTC = commande.getMontantTotal().multiply(new BigDecimal("1.20"));

        facture.setMontantTotalTTC(totalTTC);

        em.persist(facture);
        em.getTransaction().commit();
        em.close();

        return facture;
    }
}
