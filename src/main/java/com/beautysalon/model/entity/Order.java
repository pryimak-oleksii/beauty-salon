package com.example.beautysaloneeservlets.model.entity;

import com.example.beautysaloneeservlets.model.entity.enums.Status;

import java.sql.Timestamp;

public class Order extends Entity {

    private int clientId;
    private int masterId;
    private int serviceId;
    private Status status;
    private Timestamp reservationTime;
    private String clientName;
    private String masterName;
    private String serviceName;

    private Integer serviceDuration;

    private String feedBack;


    public Order() {
    }

    public Order(int clientId, int masterId, int serviceId, Status status, Timestamp reservationTime, String clientName, String masterName, String serviceName, String feedBack) {
        this.clientId = clientId;
        this.masterId = masterId;
        this.serviceId = serviceId;
        this.status = status;
        this.reservationTime = reservationTime;
        this.clientName = clientName;
        this.masterName = masterName;
        this.serviceName = serviceName;
        this.feedBack = feedBack;
    }


    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Timestamp reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public Integer getServiceDuration() {
        return serviceDuration;
    }

    public void setServiceDuration(Integer serviceDuration) {
        this.serviceDuration = serviceDuration;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
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

    @Override
    public String toString() {
        return "Order{" +
                "clientId=" + clientId +
                ", masterId=" + masterId +
                ", serviceId=" + serviceId +
                ", status=" + status +
                ", reservationTime=" + reservationTime +
                '}';
    }
}
