package org.example.restaurant.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.assertj.core.util.Lists;
import org.example.restaurant.AppContextTest;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class RestaurantControllerTest extends AppContextTest {
    @Autowired
    private MockMvc mockMvc;
    private RestaurantInDTO restaurantInDTO;
    private RestaurantOutDTO restaurantOutDTOWithoutReview;
    private RestaurantOutDTO restaurantOutDTOWithReview;
    private List<ReviewInDTO> reviewInDTOList = new ArrayList<>();


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

        ReviewInDTO reviewIn1 = ReviewInDTO.builder()
                .restaurantId(1L)
                .rate(1)
                .text("test_text_1")
                .build();

        ReviewInDTO reviewIn2 = ReviewInDTO.builder()
                .restaurantId(1L)
                .rate(3)
                .text("test_text_2")
                .build();

        reviewInDTOList = Lists.list(reviewIn1, reviewIn2);


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
        restaurantOutDTOWithReview = restaurantOutDTOBuilder
                .reviews(reviews)
                .build();
    }

    @Test
    void addRestaurant() throws Exception {
        ObjectMapper objectMapper = new JsonMapper();
        // create expected string as json from object
        // you can read this string from file
        String afterSaveRestaurant = objectMapper.writeValueAsString(restaurantOutDTOWithoutReview);
        this.mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurantInDTO)))
                .andDo(print()) //print response in console
                .andExpect(status().isOk()) // check status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // check media typeof response
                .andExpect(content().json(afterSaveRestaurant)); // check response body

        this.mockMvc.perform(post("/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewInDTOList.get(0))))
                .andDo(print()) //print response in console
                .andExpect(status().isOk()) // check status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // check media typeof response
                .andExpect(content().json("1")); // check response body

        this.mockMvc.perform(post("/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewInDTOList.get(1))))
                .andDo(print()) //print response in console
                .andExpect(status().isOk()) // check status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // check media typeof response
                .andExpect(content().string("2")); // check response body

        String afterAddReviewRestaurant = objectMapper.writeValueAsString(restaurantOutDTOWithReview);
        this.mockMvc.perform(get("/restaurant/{restaurantId}", restaurantOutDTOWithoutReview.getId()))
                .andDo(print()) //print response in console
                .andExpect(status().isOk()) // check status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // check media typeof response
                .andExpect(content().json(afterAddReviewRestaurant)); // check response body
    }

    @Test
    public void validationTest() throws Exception {
        ObjectMapper objectMapper = new JsonMapper();
        RestaurantInDTO dto = RestaurantInDTO.builder()
                .name("")
                .foundationDate(LocalDate.of(2012, 12, 12))
                .telephoneNumber("+7 (999) 999 99 99")
                .build();

        this.mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print()) //print response in console
                .andExpect(status().is4xxClientError());// check status
    }
}
