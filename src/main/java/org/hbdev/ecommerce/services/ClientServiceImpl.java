package org.hbdev.ecommerce.services;

import lombok.extern.java.Log;
import org.hbdev.ecommerce.dao.ClientDao;
import org.hbdev.ecommerce.dao.ClientDaoImpl;
import org.hbdev.ecommerce.models.Client;

import java.util.List;
@Log
public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao;

    public ClientServiceImpl() {
        this.clientDao = new ClientDaoImpl();
    }
    @Override
    public void create(Client client) {
        clientDao.insert(client);
    }

    @Override
    public Client getById(Integer id) {
        return clientDao.findById(id);
    }

    @Override
    public List<Client> getAll() {
        return clientDao.findAll();
    }

    @Override
    public Client update(Client client) {
        return clientDao.update(client);
    }

    @Override
    public void delete(Integer id) {
        clientDao.delete(id);
    }
}
