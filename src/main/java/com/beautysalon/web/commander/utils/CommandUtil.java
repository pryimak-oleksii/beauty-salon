package com.beautysalon.web.commander.utils;


import com.beautysalon.model.entity.User;
import com.beautysalon.model.entity.enums.Role;
import com.beautysalon.model.entity.enums.Status;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

public abstract class CommandUtil {


    private CommandUtil() {

    }

    public static void goToPage(HttpServletRequest req, HttpServletResponse resp, String url) {
        var requestDispatcher = req.getRequestDispatcher(url);
        try {
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static String getPersonPageByRole(User user) {

        return switch (user.getRole()) {
            case CLIENT -> "/view/main";
            case MASTER -> "/view/master";
            case ADMINISTRATOR -> "/view/admin";

        };

    }

    // TODO find the place for this method
    public static Integer getNumberOfRole(Role role) {
        return switch (role) {
            case ADMINISTRATOR -> 1;
            case MASTER -> 2;
            case CLIENT -> 3;
        };
    }

    // TODO find the place for this method
    public static Role getRoleByNumber(Integer roleNumber) {
        return switch (roleNumber) {
            case 1 -> Role.ADMINISTRATOR;
            case 2 -> Role.MASTER;
            case 3 -> Role.CLIENT;
            default -> throw new RuntimeException("There are no such role by Number");
        };
    }

    // TODO find the place for this method
    public static Integer getNumberOfStatus(Status status) {
        return switch (status) {
            case CREATED -> 1;
            case PAID -> 2;
            case DONE -> 3;
            case CANCELED -> 4;
        };
    }

    // TODO find the place for this method
    public static Status getStatusByNumber(Integer statusNumber) {
        return switch (statusNumber) {
            case 1 -> Status.CREATED;
            case 2 -> Status.PAID;
            case 3 -> Status.DONE;
            case 4 -> Status.CANCELED;
            default -> throw new RuntimeException("There are no such status by Number");
        };
    }

    public static Date getCurrentDate() {
        var cal = Calendar.getInstance();
        var sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(cal.getTime());
        return Date.valueOf(date);
    }

    public static Date getNextBill() {
        var cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 30);
        var sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(cal.getTime());
        return Date.valueOf(date);
    }

    public static Optional<String> encrypt(String pass) {
        try {
            var messageDigest = MessageDigest.getInstance("SHA-256");

            messageDigest.update(pass.getBytes());

            byte[] digest = messageDigest.digest();
            var stringBuilder = new StringBuilder();

            for (byte theByte : digest) {
                stringBuilder.append(String.format("%02x", theByte & 0xff));
            }
            return Optional.of(stringBuilder.toString());
        } catch (NoSuchAlgorithmException e) {

        }
        return Optional.empty();
    }
}
