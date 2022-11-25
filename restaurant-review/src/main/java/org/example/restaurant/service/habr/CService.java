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

    public C doSmth() throws CException {
        A a = aService.doSmth();
        B b;
        try {
            b = bService.doSmth(a);
        } catch (Exception e) {
            throw new CException();
        }
        return getCObjectFromBandA(b);
    }
    private C getCObjectFromBandA(B b) {
        return new C(b);
    }
}
