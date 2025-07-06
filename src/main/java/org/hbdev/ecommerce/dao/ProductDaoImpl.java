package org.hbdev.ecommerce.dao;

import lombok.extern.java.Log;
import org.hbdev.ecommerce.enums.Gender;
import org.hbdev.ecommerce.models.Product;
import org.hbdev.ecommerce.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log
public class ProductDaoImpl implements ProductDao {
    private final Connection connection;

    public ProductDaoImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void insert(Product client) {
        String sql = "Insert into products(name, price) values (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getName());
            statement.setDouble(2, client.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.warning("Error inserting Product");
            e.printStackTrace();
        }
    }

    @Override
    public Product findById(Integer id) {
        String sql = "select * from products where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            log.warning("Error during fetching client by Id");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "Select * from products";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            log.warning("Error during fetching Products");
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product update(Product client) {
        String sql = "Update products set name = ?, price = ? where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getName());
            statement.setDouble(2, client.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.warning("Error during update Product");
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public void delete(Integer id) {
        String sql = "Delete from products where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                log.info("Product with id " + id + " deleted successfully");
            } else {
                log.info("Product with id = " + id + " does not exist");
            }
        } catch (SQLException e) {
            log.warning("Error during delete Product");
            e.printStackTrace();
        }
    }
}
