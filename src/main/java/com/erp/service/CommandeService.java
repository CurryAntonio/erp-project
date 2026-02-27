package com.erp.service;

import com.erp.enums.EtatCommande;
import com.erp.model.*;
import com.erp.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class CommandeService {

    public Commande creerCommande(Client client, Map<Long, Integer> produitsQuantites) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Commande commande = new Commande();
        commande.setClient(em.find(Client.class, client.getId()));
        commande.setDate(LocalDate.now());
        commande.setEtat(EtatCommande.EN_ATTENTE);

        List<LigneCommande> lignes = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (Map.Entry<Long, Integer> entry : produitsQuantites.entrySet()) {
            Produit produit = em.find(Produit.class, entry.getKey());
            int qte = entry.getValue();

            if (produit == null)
                throw new RuntimeException("Produit introuvable");

            if (produit.getQuantiteStock() < qte)
                throw new RuntimeException("Stock insuffisant");

            produit.setQuantiteStock(produit.getQuantiteStock() - qte);

            LigneCommande ligne = new LigneCommande();
            ligne.setProduit(produit);
            ligne.setCommande(commande);
            ligne.setQuantite(qte);

            BigDecimal montant =
                    produit.getPrixUnitaire().multiply(BigDecimal.valueOf(qte));

            ligne.setPrixVenteTotal(montant);

            total = total.add(montant);
            lignes.add(ligne);
        }

        commande.setLignes(lignes);
        commande.setMontantTotal(total);

        Facture facture = new Facture();
        facture.setCommande(commande);
        facture.setDateFacturation(LocalDate.now());
        facture.setMontantTotalTTC(total.multiply(new BigDecimal("1.20")));

        commande.setFacture(facture);

        em.persist(commande);

        em.getTransaction().commit();
        em.close();

        return commande;
    }
}
