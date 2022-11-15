package org.example.restaurant.service.habr;

import org.example.restaurant.util.AppContextTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ServiceWithDbTest extends AppContextTest {
    @Autowired
    private ServiceWithDbForTest service;

    @Autowired
    private ServiceWithDbLazy lazyService;

    @Test
    @Transactional
    public void testTransactional() {
        service.updateData(1L, null);
        System.out.println("I'm here! :-(");
    }

    @Test
    @Transactional
    public void testLazy() {
        List<String> strings = lazyService.doSmthWithLazy(1L);
        assertArrayEquals(new String[] {"1", "2", "3", "4"}, strings.toArray());
    }


}
