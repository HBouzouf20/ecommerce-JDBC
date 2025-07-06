package org.hbdev.ecommerce.dao;

import lombok.extern.java.Log;
import org.hbdev.ecommerce.enums.Gender;
import org.hbdev.ecommerce.models.Client;
import org.hbdev.ecommerce.models.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log
public class OrderDaoImpl implements OrderDao {
    private final Connection connection;
    private final ClientDao clientDao;
    private final ProductDao productDao;
    public OrderDaoImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
        this.clientDao = new ClientDaoImpl();
        this.productDao = new ProductDaoImpl();
    }

    @Override
    public void insert(Order order) {
        String sql = "Insert into orders(client_id, product_id,quantity) values (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order.getClient().getId());
            statement.setInt(2, order.getProduct().getId());
            statement.setInt(3, order.getQuantity());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.warning("Error inserting Order");
            e.printStackTrace();
        }
    }

    @Override
    public Order findById(Integer id) {
        String sql = "select * from clients where id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return new Order(
                        rs.getInt("id"),
                       clientDao.findById(rs.getInt("client_id")),
                       productDao.findById(rs.getInt("product_id")),
                       rs.getInt("quantity")
                );
            }
        }catch (SQLException e) {
            log.warning("Error during fetching client by Id");
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Order> findAll() {
        List<Order> clients = new ArrayList<>();
        String sql = "Select * from orders";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                clients.add(new Order(
                        rs.getInt("id"),
                        clientDao.findById(rs.getInt("client_id")),
                        productDao.findById(rs.getInt("product_id")),
                        rs.getInt("quantity")
                ));
            }
        }catch (SQLException e) {
            log.warning("Error during fetching Clients");
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Order update(Order object) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}

