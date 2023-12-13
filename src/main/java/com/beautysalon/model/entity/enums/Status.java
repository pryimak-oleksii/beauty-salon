package com.example.beautysaloneeservlets.model.entity.enums;

public enum Status {
    CREATED,
    PAID,
    DONE,
    CANCELED;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
