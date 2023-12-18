package com.beautysalon.model.DAO;

import com.beautysalon.model.entity.enums.Status;

public class DAOUtils {
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
}
