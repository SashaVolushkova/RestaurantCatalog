package org.example.restaurant.repository;

import org.example.restaurant.model.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    //@Query("from ReviewEntity e join fetch e.restaurant as r where r.id = :restaurantId")
    List<ReviewEntity> findAllByRestaurantId(Long restaurantId);
}
