package org.example.restaurant.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "review")
public class ReviewEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @ManyToOne
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewEntity that = (ReviewEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(restaurant.getId(),that.restaurant.getId())
                && Objects.equals(rating, that.rating) && Objects.equals(reviewText, that.reviewText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurant, rating, reviewText);
    }
}
