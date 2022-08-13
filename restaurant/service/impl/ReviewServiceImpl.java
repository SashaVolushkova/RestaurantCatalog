package restaurant.service.impl;

import org.springframework.stereotype.Service;
import restaurant.exception.RestaurantNotFoundException;
import restaurant.model.RestaurantEntity;
import restaurant.model.ReviewEntity;
import restaurant.repository.RestaurantRepository;
import restaurant.repository.ReviewRepository;
import restaurant.service.ReviewService;

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
    public ReviewEntity getReviewTexts(Long restaurantId) {
        return (ReviewEntity) reviewRepository.findAllByRestaurantId(restaurantId).stream()
                .map(ReviewEntity::getReviewText).collect(Collectors.toList());
    }
}
