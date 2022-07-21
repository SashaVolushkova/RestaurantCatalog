package org.example.restaurant.service;

import org.example.restaurant.exception.RestaurantNotFoundException;

import java.util.List;

public interface ReviewService {
    Long addReview(Long restaurantId, String text, Integer rate) throws RestaurantNotFoundException;

    List<String> getReviewTexts(Long restaurantId);
}
