package org.example.restaurant.service.habr;

import org.example.restaurant.Application;
import org.example.restaurant.util.H2TestProfileJPAConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@SpringBootTest(classes = {
        Application.class,
        H2TestProfileJPAConfig.class})
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration")
@ActiveProfiles("test")
@Sql(value = "/data/insert_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/data/remove_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ServiceWithDbTest {
    @Autowired
    private ServiceWithDbForTest service;

    @Autowired
    private ServiceWithDbLazy lazyService;

    @Test
    @Transactional
    public void testTransactional() {
        service.updateData(1L, null);
    }

    @Test
    @Transactional
    public void testLazy() {
        List<String> strings = lazyService.doSmthWithLazy(1L);
        assertArrayEquals(new String[] {"1", "2", "3", "4"}, strings.toArray());
    }
}
