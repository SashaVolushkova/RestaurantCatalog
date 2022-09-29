package org.example.restaurant.util;

import org.example.restaurant.RestaurantApp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {
        RestaurantApp.class,
        H2TestProfileJPAConfig.class})
@ActiveProfiles("profileA")
public class AppContextTest {
    @Test
    void contextLoads() {
    }
}
