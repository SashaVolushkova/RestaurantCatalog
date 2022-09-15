package org.example.restaurant.service;

import org.assertj.core.util.Lists;
import org.example.restaurant.AppContextTest;
import org.example.restaurant.exception.FoundationDateIsExpiredException;
import org.example.restaurant.exception.RestaurantNotFoundException;
import org.example.restaurant.model.RestaurantEntity;
import org.example.restaurant.model.ReviewEntity;
import org.example.restaurant.repository.RestaurantRepository;
import org.example.restaurant.repository.ReviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mockStatic;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestaurantServiceTest extends AppContextTest {
    @Autowired
    private RestaurantRepository restaurantRepository;
    private RestaurantEntity restaurantWithReview;

    @BeforeAll
    public void beforeAll() {
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setName("test");

        ReviewEntity review1 = new ReviewEntity();
        review1.setReviewText("qqq");
        review1.setRestaurant(restaurant);
        review1.setRating(1);

        ReviewEntity review2 = new ReviewEntity();
        review2.setReviewText("qqq");
        review2.setRestaurant(restaurant);
        review2.setRating(1);

        ReviewEntity review3 = new ReviewEntity();
        review3.setReviewText("qqq");
        review3.setRestaurant(restaurant);
        review3.setRating(1);

        List<ReviewEntity> list = Lists.list(review1, review2, review3);
        restaurant.setReviews(list);
        restaurantWithReview = restaurantRepository.save(restaurant);
    }
    @Test
    @Transactional
    public void testGetReviews() {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantWithReview.getId()).get();
        for (ReviewEntity e : restaurant.getReviews()) {
            System.out.println(e.getRestaurant().getName());
        }
    }
}