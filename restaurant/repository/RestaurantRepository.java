package restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurant.model.RestaurantEntity;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}
