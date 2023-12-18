package com.beautysalon.model.DAO;


import com.beautysalon.db.DBHelper;
import com.beautysalon.model.entity.Order;
import com.beautysalon.model.entity.enums.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.beautysalon.constants.globalConstants.*;
import static com.beautysalon.model.DAO.DAOUtils.getNumberOfStatus;
import static com.beautysalon.model.DAO.DAOUtils.getStatusByNumber;



public class OrderDAO {

    public OrderDAO(UserDAO userDAO, ServiceDAO serviceDAO) {
        this.userDAO = userDAO;
        this.serviceDAO = serviceDAO;
    }


    private final UserDAO userDAO;
    private final ServiceDAO serviceDAO;


    public void changeOrderStatus(Integer orderId, Status newStatus) {
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ORDER_STATUS_BY_id)
        ) {

            statement.setInt(1, getNumberOfStatus(newStatus));
            statement.setInt(2, orderId);
            statement.executeLargeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("changeOrderStatus failed", e);
        }
    }


    public void changeOrderTime(Integer orderId, Timestamp newTime) {
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TIME_STATUS_BY_id)
        ) {
            statement.setTimestamp(1, newTime);
            statement.setInt(2, orderId);
            statement.executeLargeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("changeOrderTime failed", e);
        }
    }

    public Order getOrderById(int id) {
        Order order = new Order();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ORDER_BY_ID)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                order.setId(rs.getInt("order_id"));
                order.setClientId(rs.getInt("client_id"));
                order.setMasterId(rs.getInt("master_id"));
                order.setServiceId(rs.getInt("service_id"));
                order.setStatus(getStatusByNumber(rs.getInt("status_id")));
                order.setReservationTime(rs.getTimestamp("reservation_time"));
                order.setFeedBack(rs.getString("feed_back"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("getOrderById failed", e);
        }
        return order;
    }


    public List<Order> findAllOrders() {

        List<Order> orderList = new ArrayList<>();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_ORDERS)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {


                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setClientId(rs.getInt("client_id"));
                order.setMasterId(rs.getInt("master_id"));
                order.setServiceId(rs.getInt("service_id"));
                order.setStatus(getStatusByNumber(rs.getInt("status_id")));
                order.setReservationTime(rs.getTimestamp("reservation_time"));
                order.setFeedBack(rs.getString("feed_back"));
                orderList.add(order);
            }

            return orderList;
        } catch (SQLException e) {
            throw new RuntimeException("findAllOrders failed", e);
        }
    }

    public List<Order> findAllOrdersInfo() {

        List<Order> orderList = new ArrayList<>();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_ORDERS)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setClientName(userDAO.getUserById(rs.getInt("client_id")).getName());
                order.setMasterName(userDAO.getUserById(rs.getInt("master_id")).getName());
                order.setServiceName(serviceDAO.getServiceById(rs.getInt("service_id")).getName());
                order.setStatus(getStatusByNumber(rs.getInt("status_id")));
                order.setReservationTime(rs.getTimestamp("reservation_time"));
                order.setFeedBack(rs.getString("feed_back"));
                order.setMasterId(rs.getInt("master_id"));
                order.setServiceDuration(serviceDAO.getServiceById(rs.getInt("service_id")).getDuration());
                orderList.add(order);
            }

            return orderList;
        } catch (SQLException e) {
            throw new RuntimeException("findAllOrdersInfo failed", e);
        }
    }

    public boolean addOrder(Order order) {
// TODO do i need return statement here?
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_ORDER)) {
            statement.setInt(1, order.getClientId());
            statement.setInt(2, order.getMasterId());
            statement.setInt(3, order.getServiceId());
            statement.setInt(4, getNumberOfStatus(order.getStatus()));
            statement.setTimestamp(5, order.getReservationTime());

            statement.executeUpdate();
            return true;
        } catch (SQLException exception) {
            throw new RuntimeException("add order failed", exception);
        }


    }

}
