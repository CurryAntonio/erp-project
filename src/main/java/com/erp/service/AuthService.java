package com.erp.service;

import com.erp.dao.UtilisateurDAO;
import com.erp.model.Utilisateur;

public class AuthService {

    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    public Utilisateur login(String login, String password) {
        Utilisateur user = utilisateurDAO.findByLogin(login);

        if (user == null)
            return null;

        if (!com.erp.util.PasswordUtil.verify(password, user.getPassword()))
            return null;

        return user;
    }
}
