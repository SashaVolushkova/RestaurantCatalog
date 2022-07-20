package org.example.restaurant.service.impl;

import org.example.restaurant.exception.RestaurantNotFoundException;
import org.example.restaurant.model.RestaurantEntity;
import org.example.restaurant.model.ReviewEntity;
import org.example.restaurant.repository.RestaurantRepository;
import org.example.restaurant.repository.ReviewRepository;
import org.example.restaurant.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(RestaurantRepository restaurantRepository, ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Long addReview(Long restaurantId, String text, Integer rate) throws RestaurantNotFoundException {
        Optional<RestaurantEntity> byId = restaurantRepository.findById(restaurantId);
        if(byId.isEmpty()) {
            throw new RestaurantNotFoundException(restaurantId);
        }
        RestaurantEntity restaurantEntity = byId.get();
        ReviewEntity review = new ReviewEntity(restaurantEntity, text, rate);
        return reviewRepository.save(review).getId();
    }

    @Override
    public List<String> getReviewTexts(Long restaurantId) {
        return reviewRepository.findAllByRestaurantId(restaurantId).stream()
                .map(ReviewEntity::getReviewText).collect(Collectors.toList());
    }
}
