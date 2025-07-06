package org.hbdev.ecommerce.services;

import org.hbdev.ecommerce.dao.OrderDao;
import org.hbdev.ecommerce.dao.OrderDaoImpl;
import org.hbdev.ecommerce.models.Order;
import org.hbdev.ecommerce.models.Order;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao clientDao;

    public OrderServiceImpl() {
        this.clientDao = new OrderDaoImpl();
    }
    @Override
    public void create(Order client) {
        clientDao.insert(client);
    }

    @Override
    public Order getById(Integer id) {
        return clientDao.findById(id);
    }

    @Override
    public List<Order> getAll() {
        return clientDao.findAll();
    }

    @Override
    public Order update(Order client) {
        return clientDao.update(client);
    }

    @Override
    public void delete(Integer id) {
        clientDao.delete(id);
    }
}
