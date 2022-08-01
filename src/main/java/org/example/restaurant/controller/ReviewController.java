package org.example.restaurant.controller;

import org.example.restaurant.dto.in.ReviewInDTO;
import org.example.restaurant.dto.out.TestDto;
import org.example.restaurant.exception.RestaurantNotFoundException;
import org.example.restaurant.service.ReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/review")
    public Long addReview(@Valid @RequestBody ReviewInDTO review) throws RestaurantNotFoundException {
        return reviewService.addReview(review.getRestaurantId(), review.getText(), review.getRate());
    }
}
