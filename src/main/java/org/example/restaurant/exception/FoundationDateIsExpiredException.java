package org.example.restaurant.exception;

import java.time.LocalDate;

public class FoundationDateIsExpiredException extends Exception {
    private final String restaurantName;
    private final LocalDate foundationDate;
    public FoundationDateIsExpiredException(String name, LocalDate foundationDate) {
        this.restaurantName = name;
        this.foundationDate = foundationDate;
    }

    @Override
    public String toString() {
        return "Restaurant with name \"" + restaurantName + "\"" +
                "has foundation date " + foundationDate;
    }
}
