package com.example.beautysaloneeservlets.model.DAO;

import com.example.beautysaloneeservlets.db.DBHelper;
import com.example.beautysaloneeservlets.model.entity.User;
import com.example.beautysaloneeservlets.model.entity.enums.Days;
import com.example.beautysaloneeservlets.model.entity.enums.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.beautysaloneeservlets.web.commander.utils.CommandUtil.getNumberOfRole;
import static com.example.beautysaloneeservlets.web.commander.utils.CommandUtil.getRoleByNumber;

public class UserDAO {

    public static final String SQL_ADD_USER = "INSERT INTO users" +
            "(role_id, user_name, user_surname,  user_password," +
            " user_email) VALUES(?,?,?,?,?)";
    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";


    public User getUserById(int id) {
        return findAllUsers().stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public User getUserByName(String name) {
        return findAllUsers().stream().filter(user -> Objects.equals(user.getName(), name)).findFirst().orElse(null);
    }


    public User getUserByEmail(String email) {
        return findAllUsers().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }


    public List<User> findAllUsers() {

        List<User> userList = new ArrayList<>();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_USERS)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("users_id"));
                user.setRole(getRoleByNumber(rs.getInt("role_id")));
                user.setName(rs.getString("user_name"));
                user.setSurname(rs.getString("user_surname"));
                user.setPassword(rs.getString("user_password"));
                user.setEmail(rs.getString("user_email"));
                user.setRating(rs.getInt("user_rating"));

            
                if (user.getRole().equals(Role.MASTER)){
                    user.setWorkingDays(getMasterWorkingDays(user.getId()));

                }
                userList.add(user);
            }

            return userList;
        } catch (SQLException e) {
            throw new RuntimeException("findAllUsers failed", e);
        }
    }

    public boolean addUser(User user) {

        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER)){

            statement.setInt(1, getNumberOfRole(user.getRole()));
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());

            statement.executeUpdate();
            return true;

        } catch (SQLException exception) {
            throw new RuntimeException("add user failed", exception);
        }

    }

    public List<Integer> getMasterWorkingDaysNumbers(Integer masterId){

        List<Integer> resultList = new ArrayList<>();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM master_schedule")) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
              if (masterId == rs.getInt("master_id")){
                  resultList.add(rs.getInt("day_id"));
              }
            }

            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException("findAllUsers failed", e);
        }
    }


    public List<Days> getMasterWorkingDays(Integer masterId){

        List<Days> resultList = new ArrayList<>();
        try (Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM master_schedule")) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (masterId == rs.getInt("master_id")){
                    resultList.add(Days.getDayByNumber(rs.getInt("day_id")));
                }
            }

            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException("getMasterWorkingDays failed", e);
        }
    }



}
