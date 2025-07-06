package org.hbdev.ecommerce.services;

import org.hbdev.ecommerce.dao.ProductDao;
import org.hbdev.ecommerce.dao.ProductDaoImpl;
import org.hbdev.ecommerce.models.Product;
import org.hbdev.ecommerce.models.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDao clientDao;

    public ProductServiceImpl() {
        this.clientDao = new ProductDaoImpl();
    }
    @Override
    public void create(Product client) {
        clientDao.insert(client);
    }

    @Override
    public Product getById(Integer id) {
        return clientDao.findById(id);
    }

    @Override
    public List<Product> getAll() {
        return clientDao.findAll();
    }

    @Override
    public Product update(Product client) {
        return clientDao.update(client);
    }

    @Override
    public void delete(Integer id) {
        clientDao.delete(id);
    }
}
