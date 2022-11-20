package org.example.restaurant.service.habr;

import org.example.restaurant.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@SpringBootTest(classes = {Application.class})
@ActiveProfiles("test")
@Sql(value = "/data/insert_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/data/remove_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Testcontainers
public class ServiceWithDbTest {
    @Container
    private static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer()
            .withDatabaseName("foo")
            .withUsername("foo")
            .withPassword("secret");

    @DynamicPropertySource
    static void dataSourceProperty(DynamicPropertyRegistry registry) {
        postgresqlContainer.start();
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
    }
    @Autowired
    private ServiceWithDbForTest service;

    @Autowired
    private ServiceWithDbLazy lazyService;

    @Test
    public void testTransactional() {
        service.updateData(1L, null);
    }

    @Test
    public void testLazy() {
        List<String> strings = lazyService.doSmthWithLazy(1L);
        assertArrayEquals(new String[] {"1", "2", "3", "4"}, strings.toArray());
    }
}
