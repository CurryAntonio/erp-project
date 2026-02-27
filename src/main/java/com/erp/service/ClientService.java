package com.erp.service;

import com.erp.dao.ClientDAO;
import com.erp.model.Client;

import java.util.List;

public class ClientService {

    private ClientDAO clientDAO = new ClientDAO();

    public void creerClient(Client client) {
        clientDAO.save(client);
    }

    public Client trouverClient(Long id) {
        return clientDAO.find(id);
    }

    public List<Client> listerClients() {
        return clientDAO.findAll();
    }

    public Client modifierClient(Client client) {
        return clientDAO.update(client);
    }

    public void supprimerClient(Long id) {
        clientDAO.delete(id);
    }
}
