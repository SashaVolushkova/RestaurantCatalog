package org.example.employee.util;

import org.example.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {Application.class})
@ActiveProfiles("test")
public class AppContextTest {
    @Test
    void contextLoads() {
    }
}
