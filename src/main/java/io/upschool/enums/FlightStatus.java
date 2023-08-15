package io.upschool.enums;

public enum FlightStatus {
    ON_TIME, CANCELED;

    public static boolean contains(String value) {
        for (FlightStatus flightStatus : FlightStatus.values()) {
            if (flightStatus.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
