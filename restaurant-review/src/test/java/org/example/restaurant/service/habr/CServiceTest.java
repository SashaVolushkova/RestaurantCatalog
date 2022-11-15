package org.example.restaurant.service.habr;

import org.example.restaurant.service.habr.model.C;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CServiceTest {
    AService mockA = Mockito.mock(AService.class);
    BService mockB = Mockito.mock(BService.class);
    private final CService service = new CService(mockA, mockB);

    @Test
    public void testSomething() {
        C c = service.doSmth();
        assertNotNull(c);
    }
}
