package com.erp.service;

import com.erp.dao.FactureDAO;
import com.erp.model.Facture;
import java.util.List;

public class FactureService {
    private FactureDAO factureDAO = new FactureDAO();

    public List<Facture> getFacturesForUser(Long clientId, boolean isAdminOrManager) {
        if (isAdminOrManager) {
            return factureDAO.findAll();
        } else {
            return factureDAO.findByClientId(clientId);
        }
    }
}