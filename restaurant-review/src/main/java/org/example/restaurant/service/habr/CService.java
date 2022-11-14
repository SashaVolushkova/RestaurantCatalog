package org.example.restaurant.service.habr;

import lombok.RequiredArgsConstructor;
import org.example.restaurant.service.habr.model.A;
import org.example.restaurant.service.habr.model.B;
import org.example.restaurant.service.habr.model.C;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CService {
    private final AService aService;
    private final BService bService;

    public C doSmth() {
        A a = aService.doSmth();
        B b = bService.doSmth(a);
        return getCObjectFromBandA(a, b);
    }

    private C getCObjectFromBandA(A a, B b) {
        return new C();
    }
}
