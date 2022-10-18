package org.example.restaurant.abc;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class C {
    @Getter
    private final A a;

    public C(A a) {
        this.a = a;
    }
}
