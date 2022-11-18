package org.example.restaurant.util;

import org.example.restaurant.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {
        Application.class,
        H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
public class AppContextTest {
    @Test
    void contextLoads() {
    }
}
