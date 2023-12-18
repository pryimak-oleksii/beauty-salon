package com.beautysalon.model.entity.enums;

public enum Days {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public static Integer getNumberOfDay(Days days) {

        return switch (days) {
            case MONDAY -> 0;
            case TUESDAY -> 1;
            case WEDNESDAY -> 2;
            case THURSDAY -> 3;
            case FRIDAY -> 4;
            case SATURDAY -> 5;
            case SUNDAY -> 6;
        };

    }


    public static Days getDayByNumber(Integer number) {

        return switch (number) {
            case 0 -> MONDAY;
            case 1 -> TUESDAY;
            case 2 -> WEDNESDAY;
            case 3 -> THURSDAY;
            case 4 -> FRIDAY;
            case 5 -> SATURDAY;
            case 6 -> SUNDAY;
            default -> throw new RuntimeException("there are no such Day by number");
        };

    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
