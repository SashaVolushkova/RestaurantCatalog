package org.example.restaurant.notready;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.assertj.core.util.Lists;
import org.example.restaurant.util.AppContextTest;
import org.example.restaurant.dto.in.RestaurantInDTO;
import org.example.restaurant.dto.in.ReviewInDTO;
import org.example.restaurant.dto.out.RestaurantOutDTO;
import org.example.restaurant.dto.out.ReviewOutDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class RestaurantControllerTest extends AppContextTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    private RestaurantInDTO restaurantInDTO;
    private RestaurantOutDTO restaurantOutDTOWithoutReview;


    @BeforeEach
    void beforeAddRestaurant() {
        ReviewOutDTO review1 = ReviewOutDTO.builder()
                .id(1L)
                .rate(1)
                .text("test_text_1")
                .build();

        ReviewOutDTO review2 = ReviewOutDTO.builder()
                .id(2L)
                .rate(3)
                .text("test_text_2")
                .build();

        List<ReviewOutDTO> reviews = Lists.list(review1, review2);


        restaurantInDTO = RestaurantInDTO.builder()
                .name("test")
                .foundationDate(LocalDate.of(2012, 12, 12))
                .telephoneNumber("+7 (999) 999 99 99")
                .build();

        RestaurantOutDTO.RestaurantOutDTOBuilder restaurantOutDTOBuilder = RestaurantOutDTO.builder()
                .id(1L)
                .name("test")
                .foundationDate(LocalDate.of(2012, 12, 12))
                .telephoneNumber("+79999999999")
                .reviews(reviews);

        restaurantOutDTOWithoutReview = restaurantOutDTOBuilder
                .reviews(null)
                .build();
    }

    @Test
    void addRestaurant() throws Exception {
        // create expected string as json from object
        // you can read this string from file
        MvcResult mvcResult = this.mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurantInDTO)))
                .andDo(print()) //print response in console
                .andExpect(status().isOk()) // check status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(restaurantOutDTOWithoutReview.getName()))
                .andExpect(jsonPath("$.id").hasJsonPath())
                .andExpect(jsonPath("$.foundationDate").value(restaurantOutDTOWithoutReview.getFoundationDate().format(DateTimeFormatter.ISO_DATE)))
                .andExpect(jsonPath("$.telephoneNumber").value(restaurantOutDTOWithoutReview.getTelephoneNumber()))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        restaurantOutDTOWithoutReview = objectMapper.readValue(contentAsString, RestaurantOutDTO.class);

        ReviewInDTO reviewIn1 = ReviewInDTO.builder()
                .restaurantId(restaurantOutDTOWithoutReview.getId())
                .rate(1)
                .text("test_text_1")
                .build();

        ReviewInDTO reviewIn2 = ReviewInDTO.builder()
                .restaurantId(restaurantOutDTOWithoutReview.getId())
                .rate(3)
                .text("test_text_2")
                .build();



        this.mockMvc.perform(post("/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewIn1)))
                .andDo(print()) //print response in console
                .andExpect(status().isOk()) // check status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));


        this.mockMvc.perform(post("/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewIn2)))
                .andDo(print()) //print response in console
                .andExpect(status().isOk()) // check status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(get("/restaurant/{restaurantId}", restaurantOutDTOWithoutReview.getId()))
                .andDo(print()) //print response in console
                .andExpect(status().isOk()) // check status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // check media typeof response
                .andExpect(jsonPath("$.id").value(restaurantOutDTOWithoutReview.getId()))
                .andExpect(jsonPath("$.name").value(restaurantOutDTOWithoutReview.getName()))
                .andExpect(jsonPath("$.telephoneNumber").value(restaurantOutDTOWithoutReview.getTelephoneNumber()))
                .andExpect(jsonPath("$.foundationDate").value(restaurantOutDTOWithoutReview.getFoundationDate().format(DateTimeFormatter.ISO_DATE)))
                .andExpect(jsonPath("$.reviews").isArray())
                .andExpect(jsonPath("$.reviews").isNotEmpty()); // check response body
    }

    @Test
    public void restaurantNotFound() throws Exception {
        this.mockMvc.perform(get("/restaurant/{restaurantId}", 9999))
                .andDo(print()) //print response in console
                .andExpect(status().isNotFound())
                .andExpect(status().reason("ресторан не найден"));
    }

    @Test
    public void validationTest() throws Exception {
        ObjectMapper objectMapper = new JsonMapper();
        RestaurantInDTO dto = RestaurantInDTO.builder()
                .name("")
                .foundationDate(LocalDate.now().plusDays(6))
                .telephoneNumber("bfbfdbdf")
                .build();

        this.mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print()) //print response in console
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{\"name\":\"пустое имя\"," +
                        "\"telephoneNumber\": \"телефонный номер не соответсвует формату\"}"
                                //"\"telephoneNumber\": \"пустой телефонный номер\"}"
                        //+ ",\"foundationDate\": \"будущее\"}"
                ));// check status
    }

    @Test
    public void checkFoundationDateException() throws Exception {
        ObjectMapper objectMapper = new JsonMapper();
        LocalDate date = LocalDate.now().plusDays(6);
        RestaurantInDTO dto = RestaurantInDTO.builder()
                .name("test")
                .foundationDate(date)
                .telephoneNumber("+7 999 999 99 99")
                .build();

        String result = "Restaurant with name \"" + "test" + "\"" +
                "has foundation date " + date;
        this.mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print()) //print response in console
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(result));
    }
}
