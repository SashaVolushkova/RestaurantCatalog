package restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurant.model.ReviewEntity;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findAllByRestaurantId(Long restaurantId);
}
