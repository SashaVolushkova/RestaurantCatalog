package org.example.restaurant.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "review")
public class ReviewEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurant_id", nullable=false)
    private RestaurantEntity restaurant;
    @Basic
    @Column(name = "rating")
    private Integer rating;
    @Basic
    @Column(name = "review_text")
    private String reviewText;

    public ReviewEntity() {

    }

    public ReviewEntity(RestaurantEntity restaurantEntity, String text, Integer rate) {
        this.restaurant = restaurantEntity;
        this.reviewText = text;
        this.rating = rate;
    }
}
