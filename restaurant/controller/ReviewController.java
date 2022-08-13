package restaurant.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import restaurant.dto.in.ReviewInDTO;
import restaurant.dto.out.ReviewOutDTO;
import restaurant.exception.RestaurantNotFoundException;
import restaurant.mapper.ReviewMapper;
import restaurant.model.RestaurantEntity;
import restaurant.model.ReviewEntity;
import restaurant.service.ReviewService;

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
    @PostMapping("/review{reviewId}")
    public Long getReview(@PathVariable Long reviewId) {
        ReviewEntity reviewEntity = reviewService.getReviewTexts(reviewId);
        return ReviewMapper.INSTANCE.toDto(reviewEntity);
    }

}
