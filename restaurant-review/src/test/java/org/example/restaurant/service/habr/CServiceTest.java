package org.example.restaurant.service.habr;

import org.example.restaurant.service.habr.model.B;
import org.example.restaurant.service.habr.model.C;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CServiceTest {
    AService mockA = mock(AService.class);
    BService mockB = mock(BService.class);
    private final CService service = new CService(mockA, mockB);

    @Test
    public void testSomething() {
        B b = new B(false);
        when(mockB.doSmth(any())).thenReturn(b);
        C c = service.doSmth();
        assertNotNull(c);
    }
}
