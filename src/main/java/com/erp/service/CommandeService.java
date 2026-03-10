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
        try {
            Commande commande = new Commande();
            commande.setClient(em.find(Client.class, client.getId()));
            commande.setDate(LocalDate.now());
            commande.setEtat(EtatCommande.EN_ATTENTE);

            // ... Logique de création de lignes et mise à jour stock ...

            em.persist(commande);
            em.getTransaction().commit();
            return commande;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * NOUVELLE MÉTHODE : Récupère uniquement les commandes d'un client.
     */
    public List<Commande> findByClient(Long clientId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Commande c WHERE c.client.id = :clientId", Commande.class)
                    .setParameter("clientId", clientId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void validerCommande(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Commande c = em.find(Commande.class, id);
            if (c != null) {
                c.setEtat(EtatCommande.VALIDEE);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Commande> listerCommandes() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Commande c", Commande.class).getResultList();
        } finally {
            em.close();
        }
    }
}