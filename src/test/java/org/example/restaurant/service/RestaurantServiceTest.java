package org.example.restaurant.service;

import org.assertj.core.util.Lists;
import org.example.restaurant.AppContextTest;
import org.example.restaurant.exception.FoundationDateIsExpiredException;
import org.example.restaurant.exception.RestaurantNotFoundException;
import org.example.restaurant.model.FoodTypeEntity;
import org.example.restaurant.model.RestaurantEntity;
import org.example.restaurant.model.ReviewEntity;
import org.example.restaurant.repository.FoodTypeRepository;
import org.example.restaurant.repository.RestaurantRepository;
import org.example.restaurant.repository.ReviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mockStatic;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestaurantServiceTest extends AppContextTest {
    @Autowired
    private RestaurantRepository restaurantRepository;
    private RestaurantEntity restaurantWithReview;
    private ReviewEntity savedReview;

    @BeforeAll
    public void beforeAll() {
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setName("test");

        ReviewEntity review1 = new ReviewEntity();
        review1.setReviewText("qqq");
        review1.setRestaurant(restaurant);
        review1.setRating(1);

        ReviewEntity review2 = new ReviewEntity();
        review2.setReviewText("qqq");
        review2.setRestaurant(restaurant);
        review2.setRating(1);

        ReviewEntity review3 = new ReviewEntity();
        review3.setReviewText("qqq");
        review3.setRestaurant(restaurant);
        review3.setRating(1);

        List<ReviewEntity> list = Lists.list(review1, review2, review3);
        restaurant.setReviews(list);
        savedReview = review1;
        restaurantWithReview = restaurantRepository.save(restaurant);
    }
//    @Test
//    @Transactional
//    public void testGetReviews() {
//        RestaurantEntity restaurant = restaurantRepository.findById(restaurantWithReview.getId()).get();
//        for (ReviewEntity e : restaurant.getReviews()) {
//            System.out.println(e.getRestaurant().getName());
//        }
//    }

//    @Autowired
//    private ReviewRepository reviewRepository;
//    @Test
//    @Transactional
//    public void testGetReviews() {
//        List<ReviewEntity> reviewEntityList = reviewRepository.findAllByRestaurantId(restaurantWithReview.getId());
//        for (ReviewEntity e : reviewEntityList) {
//            System.out.println(e.getRestaurant().getName());
//        }
//    }

    @Autowired
    private ReviewRepository reviewRepository;
    @Test
    @Transactional
    public void testGetReviews() {
        List<ReviewEntity> reviewEntityList = reviewRepository.findAllByRestaurantId(restaurantWithReview.getId());
        for (ReviewEntity e : reviewEntityList) {
            System.out.println(e.getRestaurant().getName());
        }
    }

    @Test
    @Transactional
    public void testGetReviews2() {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantWithReview.getId()).get();
        for (ReviewEntity e : restaurant.getReviews()) {
            System.out.println(e.getRestaurant().getName());
        }
    }



    @Test
    @Transactional
    public void test3() {
        Optional<ReviewEntity> byId1 = reviewRepository.findById(savedReview.getId());
        Optional<ReviewEntity> byId2 = reviewRepository.findById(savedReview.getId());
        assertSame(byId1.get(), byId2.get());
    }

    @Autowired
    private FoodTypeRepository foodTypeRepository;
    @Test
    @Transactional
    public void test12() {
        FoodTypeEntity test11_1 = foodTypeRepository.findByName("test11");
        FoodTypeEntity test11_2 = foodTypeRepository.findByName("test11");
        assertSame(test11_2, test11_1);
    }

    @Test
    @Transactional
    public void test4() {
        Optional<RestaurantEntity> byId1 = restaurantRepository.findById(restaurantWithReview.getId());
        Optional<RestaurantEntity> byId2 = restaurantRepository.findById(restaurantWithReview.getId());
        Assert.isTrue(byId1.get() == byId2.get());
    }

    @Test
    public void test5() {
        Optional<RestaurantEntity> byId1 = restaurantRepository.findById(restaurantWithReview.getId());
        Optional<RestaurantEntity> byId2 = restaurantRepository.findById(restaurantWithReview.getId());
        assertSame(byId1.get(), byId2.get());
    }

    @Test
    public void test6() {
        Optional<RestaurantEntity> byId = restaurantRepository.findById(restaurantWithReview.getId());
        assertEquals(3, byId.get().getReviews().size());
    }

    @Test
    public void test7() {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setName("test 7");
        FoodTypeEntity foodTypeEntity = new FoodTypeEntity();
        foodTypeEntity.setName("test 7");
        foodTypeEntity.setDescription("test 7");
        restaurantEntity.setFoodType(foodTypeEntity);
        restaurantRepository.save(restaurantEntity);
    }

    @Autowired
    ServiceForTest serviceForTest;

    @Test
    public void test8() {
        try {
            serviceForTest.test8("test8");
        } catch (RuntimeException e) {
            System.out.println("error");
        }
        RestaurantEntity byId = restaurantRepository.findFirstByName("test8");
        assertNotNull(byId);
        assertEquals("test8", byId.getName());
    }
    @Test
    public void test9() {
        try {
            serviceForTest.test9("test8");
        } catch (RuntimeException e) {
            System.out.println("error");
        }
        RestaurantEntity byId = restaurantRepository.findFirstByName("test8");
        assertNotNull(byId);
        assertEquals("test8", byId.getName());
    }
    @Test
    public void test10() {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setName("test10");
        restaurantRepository.save(restaurantEntity);
        serviceForTest.test10("test10");
        Optional<RestaurantEntity> byId = restaurantRepository.findById(restaurantEntity.getId());
        assertTrue(byId.isPresent());
        assertEquals("test10Updated", byId.get().getName());
    }

    @Test
    public void test11() {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setName("test11");
        restaurantRepository.save(restaurantEntity);
        serviceForTest.test11("test11");
        Optional<RestaurantEntity> byId = restaurantRepository.findById(restaurantEntity.getId());
        assertTrue(byId.isPresent());
        assertEquals("test11Updated", byId.get().getName());
    }


    @Autowired
    private EntityManager entityManager;
    @Test
    @Transactional
    void testSessionNotEquals() {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setName("test");
        restaurantEntity = restaurantRepository.save(restaurantEntity);
        entityManager.detach(restaurantEntity);
        Optional<RestaurantEntity> byId2 = restaurantRepository.findById(restaurantEntity.getId());
        RestaurantEntity restaurant2 = byId2.get();
        assertTrue(restaurant2 == restaurantEntity);
    }


}