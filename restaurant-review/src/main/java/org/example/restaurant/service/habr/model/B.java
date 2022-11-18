package org.example.restaurant.service.habr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class B {
    public enum Enum {
        One, Two
    }
    private boolean smth;
    private Enum type;
    public boolean isSmth() {
        return smth;
    }
}
