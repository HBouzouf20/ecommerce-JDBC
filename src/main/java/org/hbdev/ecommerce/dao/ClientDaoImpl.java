package org.hbdev.ecommerce.dao;

import lombok.extern.java.Log;
import org.hbdev.ecommerce.enums.Gender;
import org.hbdev.ecommerce.models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log
public class ClientDaoImpl implements ClientDao {
    private final Connection connection;
    public ClientDaoImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    @Override
    public void insert(Client client) {
        String sql = "Insert into clients(full_name, email,phone, address, gender) values (?,?,?,?,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getFullName());
            statement.setString(2, client.getEmail());
            statement.setString(3, client.getPhone());
            statement.setString(4, client.getAddress());
            statement.setString(5, client.getGender()== null ? Gender.MALE.name() : client.getGender().name());
            statement.executeUpdate();
        }catch (SQLException e) {
            log.warning("Error inserting Client");
            e.printStackTrace();
        }
    }

    @Override
    public Client findById(Integer id) {
        String sql = "select * from clients where id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return new Client(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        Gender.valueOf(rs.getString("gender"))
                );
            }
        }catch (SQLException e) {
            log.warning("Error during fetching client by Id");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "Select * from clients";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
               clients.add(new Client(
                       rs.getInt("id"),
                       rs.getString("full_name"),
                       rs.getString("phone"),
                       rs.getString("email"),
                       rs.getString("address"),
                       Gender.valueOf(rs.getString("gender"))
               ));
            }
        }catch (SQLException e) {
            log.warning("Error during fetching Clients");
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client update(Client client) {
        String sql = "Update clients set full_name = ?, phone=?, email = ?, address = ?, gender = ? where id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getFullName());
            statement.setString(2, client.getPhone());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getAddress());
            statement.setString(5, client.getGender()== null ? Gender.MALE.toString() : client.getGender().toString());
            statement.executeUpdate();
        }catch (SQLException e) {
        log.warning("Error during update Client");
        e.printStackTrace();
    }
        return client;
    }

    @Override
    public void delete(Integer id) {
        String sql = "Delete from clients where id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            if(rows > 0) {
                log.info("Client with id " + id + " deleted successfully");
            }
            else {
                log.info("Client with id = " + id + " does not exist");
            }
        }catch (SQLException e) {
            log.warning("Error during delete Client");
            e.printStackTrace();
        }
    }
}
