package org.hbdev.ecommerce.services;

import org.hbdev.ecommerce.dao.ProviderDao;
import org.hbdev.ecommerce.dao.ProviderDaoImpl;
import org.hbdev.ecommerce.models.Provider;
import org.hbdev.ecommerce.models.Provider;

import java.util.List;

public class ProviderServiceImpl implements ProviderService {
    private final ProviderDao clientDao;

    public ProviderServiceImpl() {
        this.clientDao = new ProviderDaoImpl();
    }
    @Override
    public void create(Provider client) {
        clientDao.insert(client);
    }

    @Override
    public Provider getById(Integer id) {
        return clientDao.findById(id);
    }

    @Override
    public List<Provider> getAll() {
        return clientDao.findAll();
    }

    @Override
    public Provider update(Provider client) {
        return clientDao.update(client);
    }

    @Override
    public void delete(Integer id) {
        clientDao.delete(id);
    }
}
