package com.beautysalon.model.DAO;


import com.beautysalon.db.DBHelper;
import com.beautysalon.model.entity.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.beautysalon.constants.globalConstants.SQL_SELECT_SERVICE_BY_ID;
import static com.beautysalon.constants.globalConstants.SQL_SELECT_SERVICE_BY_NAME;


public class ServiceDAO {



    public Service getServiceById(int id) {

        Service service = new Service();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_SERVICE_BY_ID)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                service.setId(rs.getInt("service_id"));
                service.setName(rs.getString("service_name"));
                service.setDescription(rs.getString("service_description"));
                service.setPrice(rs.getBigDecimal("service_price"));
                service.setDuration(rs.getInt("service_duration"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("getOrderById failed", e);
        }
        return service;
    }

    public Service getServiceByName(String name) {
        Service service = new Service();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_SERVICE_BY_NAME)) {

            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                service.setId(rs.getInt("service_id"));
                service.setName(rs.getString("service_name"));
                service.setDescription(rs.getString("service_description"));
                service.setPrice(rs.getBigDecimal("service_price"));
                service.setDuration(rs.getInt("service_duration"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("getOrderById failed", e);
        }
        return service;
    }

    public List<Service> findAllService() {

        List<Service> services = new ArrayList<>();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM services")) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getInt("service_id"));
                service.setName(rs.getString("service_name"));
                service.setDescription(rs.getString("service_description"));
                service.setPrice(rs.getBigDecimal("service_price"));
                service.setDuration(rs.getInt("service_duration"));
                services.add(service);
            }

            return services;
        } catch (SQLException e) {
            throw new RuntimeException("findAllService failed", e);
        }
    }


    public List<Service> findAllMasterServices(Integer masterId) {

        List<Service> masterServices = new ArrayList<>();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM master_services")) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (rs.getInt("master_id") == masterId) {
                    masterServices.add(getServiceById(rs.getInt("service_id")));
                }
            }

            return masterServices;
        } catch (SQLException e) {
            throw new RuntimeException("findAllService failed", e);
        }
    }
}
