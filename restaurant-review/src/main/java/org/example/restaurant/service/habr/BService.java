package org.example.restaurant.service.habr;

import org.example.restaurant.service.habr.model.A;
import org.example.restaurant.service.habr.model.B;
import org.springframework.stereotype.Service;

@Service
public class BService {
    public B doSmth(A a) {
        return creatBFromA(a);
    }

    private B creatBFromA(A a) {
        return new B(false);
    }
}
