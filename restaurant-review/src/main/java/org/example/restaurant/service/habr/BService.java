package org.example.restaurant.service.habr;

import org.example.restaurant.service.habr.model.A;
import org.example.restaurant.service.habr.model.B;
import org.springframework.stereotype.Service;

@Service
public class BService {
    public B doSmth(A a) throws Exception {
        return creatBFromA(a);
    }

    private B creatBFromA(A a) throws Exception {
        return switch (a.getAnInt()) {
            case 1: yield  new B(false, B.Enum.One);
            case 2: yield  new B(true, B.Enum.Two);
            default: throw new Exception();
        };
    }
}
