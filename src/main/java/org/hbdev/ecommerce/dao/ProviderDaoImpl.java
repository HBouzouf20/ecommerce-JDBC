package org.hbdev.ecommerce.dao;

import lombok.extern.java.Log;
import org.hbdev.ecommerce.models.Provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log
public class ProviderDaoImpl implements ProviderDao {
    private final Connection connection;
    public ProviderDaoImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    @Override
    public void insert(Provider client) {
        String sql = "Insert into providers(full_name, email,phone, address, ICE, RIB, company_name) values (?,?,?,?,?,?,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getFullName());
            statement.setString(2, client.getEmail());
            statement.setString(3, client.getPhone());
            statement.setString(4, client.getAddress());
            statement.setString(5, client.getICE());
            statement.setString(6, client.getRIB());
            statement.setString(7, client.getCompanyName());
            statement.executeUpdate();
        }catch (SQLException e) {
            log.warning("Error inserting Provider");
            e.printStackTrace();
        }
    }

    @Override
    public Provider findById(Integer id) {
        String sql = "select * from providers where id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return new Provider(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("ICE"),
                        rs.getString("RIB"),
                        rs.getString("company_name")
                );
            }
        }catch (SQLException e) {
            log.warning("Error during fetching client by Id");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Provider> findAll() {
        List<Provider> providers = new ArrayList<>();
        String sql = "Select * from providers";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                providers.add(new Provider(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("ICE"),
                        rs.getString("RIB"),
                        rs.getString("company_name")
                ));
            }
        }catch (SQLException e) {
            log.warning("Error during fetching Providers");
            e.printStackTrace();
        }
        return providers;
    }

    @Override
    public Provider update(Provider client) {
        String sql = "Update providers set full_name = ?, phone=?, email = ?, address = ?, gender = ?,ICE = ?, RIB= ?, company_name= ? where id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getFullName());
            statement.setString(2, client.getEmail());
            statement.setString(3, client.getPhone());
            statement.setString(4, client.getAddress());
            statement.setString(5, client.getICE());
            statement.setString(6, client.getRIB());
            statement.setString(7, client.getCompanyName());
            statement.executeUpdate();
        }catch (SQLException e) {
            log.warning("Error during update Provider");
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public void delete(Integer id) {
        String sql = "Delete from providers where id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            if(rows > 0) {
                log.info("Provider with id " + id + " deleted successfully");
            }
            else {
                log.info("Provider with id = " + id + " does not exist");
            }
        }catch (SQLException e) {
            log.warning("Error during delete Provider");
            e.printStackTrace();
        }
    }
}
