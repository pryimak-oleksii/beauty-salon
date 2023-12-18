package com.beautysalon.model.entity.enums;

public enum Role {
    ADMINISTRATOR,
    CLIENT,
    MASTER;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }


}
