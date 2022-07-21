package org.example.restaurant.service;

import org.example.restaurant.AppContextTest;
import org.example.restaurant.exception.RestaurantNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReviewServiceTest extends AppContextTest {
    private Long restaurantWithReviewId;
    private Long restaurantWithoutReviewId;

    @Autowired
    ReviewService reviewService;

    @Autowired
    RestaurantService restaurantService;

    @BeforeAll
    void setUp() {
        String RESTAURANT_WITH_REVIEW = "restaurant_with_review";
        restaurantWithReviewId = restaurantService.createRestaurantByName(RESTAURANT_WITH_REVIEW);
        String RESTAURANT_WITHOUT_REVIEW = "restaurant_without_review";
        restaurantWithoutReviewId = restaurantService.createRestaurantByName(RESTAURANT_WITHOUT_REVIEW);
    }

    @Test
    void addReview() throws RestaurantNotFoundException {
        reviewService.addReview(restaurantWithReviewId, "test_review_text", 10);
        List<String> reviewTexts = reviewService.getReviewTexts(restaurantWithReviewId);
        assertEquals(1, reviewTexts.size());
        assertEquals("test_review_text", reviewTexts.get(0));
        Assertions.assertThrows(RestaurantNotFoundException.class, () -> reviewService.addReview(333L, "qqq", 33));
    }


    @Test
    void getReviewTexts() {
        List<String> reviewTexts = reviewService.getReviewTexts(restaurantWithoutReviewId);
        assertEquals(0, reviewTexts.size());
    }
}