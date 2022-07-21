package org.example.restaurant;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.example.restaurant.dto.TestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc // Annotation that can be applied to a test class to enable and configure auto-configuration of MockMvc.
public class ControllerTest extends AppContextTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testController() throws Exception {
        ObjectMapper objectMapper = new JsonMapper();
        // create expected string as json from object
        // you can read this string from file
        String expected = objectMapper.writeValueAsString(new TestDto("test"));
        this.mockMvc.perform(get("/test"))
                .andDo(print()) //print response in console
                .andExpect(status().isOk()) // check status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // check media typeof response
                .andExpect(content().json(expected)); // check response body
    }

}
