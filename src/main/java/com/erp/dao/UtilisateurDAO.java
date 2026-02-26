package com.erp.dao;

import com.erp.model.Utilisateur;
import jakarta.persistence.EntityManager;
import com.erp.util.JPAUtil;

public class UtilisateurDAO extends AbstractDAO<Utilisateur> {

    public UtilisateurDAO() {
        super(Utilisateur.class);
    }

    public Utilisateur findByLogin(String login) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT u FROM Utilisateur u WHERE u.login = :login", Utilisateur.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
