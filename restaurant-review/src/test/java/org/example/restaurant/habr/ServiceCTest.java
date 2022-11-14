package org.example.restaurant.habr;

import org.example.restaurant.service.habr.AService;
import org.example.restaurant.service.habr.BService;
import org.example.restaurant.service.habr.CService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServiceCTest {
    private final CService service;

    public ServiceCTest() {
        AService mockA = Mockito.mock(AService.class);
        BService mockB = Mockito.mock(BService.class);
        service = new CService(mockA, mockB);
    }

    @Test
    public void testSomething() {
        service.doSmth();
    }
}
