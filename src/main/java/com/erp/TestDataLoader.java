package com.erp;

import com.erp.dao.UtilisateurDAO;
import com.erp.enums.RoleUtilisateur;
import com.erp.model.Utilisateur;
import com.erp.util.PasswordUtil;

public class TestDataLoader {

    public static void main(String[] args) {

        Utilisateur admin = new Utilisateur();
        admin.setLogin("admin");
        admin.setPassword(PasswordUtil.hash("admin123"));
        admin.setRole(RoleUtilisateur.ADMIN);

        new UtilisateurDAO().save(admin);

        System.out.println("ADMIN créé avec succès !");
    }
}