package org.example.restaurant.service.habr;

import org.example.restaurant.model.RestaurantEntity;
import org.example.restaurant.service.habr.model.A;
import org.springframework.stereotype.Service;

@Service
public class AService {

    public A doSmth() {
        return new A();
    }
}
