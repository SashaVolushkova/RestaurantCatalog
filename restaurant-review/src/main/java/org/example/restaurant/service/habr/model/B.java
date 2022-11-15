package org.example.restaurant.service.habr.model;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class B {
    private boolean smth = false;

    public boolean isSmth() {
        return smth;
    }
}
