package restaurant.service;
import restaurant.exception.RestaurantNotFoundException;
import restaurant.model.ReviewEntity;

public interface ReviewService {
    Long addReview(Long restaurantId, String text, Integer rate) throws RestaurantNotFoundException;

    ReviewEntity getReviewTexts(Long restaurantId);
}
