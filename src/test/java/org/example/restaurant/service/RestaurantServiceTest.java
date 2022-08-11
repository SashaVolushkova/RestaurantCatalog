package org.example.restaurant.service;

import org.assertj.core.util.Lists;
import org.example.restaurant.AppContextTest;
import org.example.restaurant.exception.FoundationDateIsExpiredException;
import org.example.restaurant.exception.RestaurantNotFoundException;
import org.example.restaurant.model.RestaurantEntity;
import org.example.restaurant.model.ReviewEntity;
import org.example.restaurant.repository.RestaurantRepository;
import org.example.restaurant.repository.ReviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mockStatic;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestaurantServiceTest extends AppContextTest {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    private RestaurantEntity restaurantWithReview;

    @Test
    void createRestaurantByName() {
        String name = "testName";
        long restaurantByName = restaurantService.createRestaurantByName(name);
        assertTrue(restaurantByName > 0);
    }

    @Test
    void getRestaurantNameById() throws RestaurantNotFoundException {
        String name = "testName";
        long restaurantByName = restaurantService.createRestaurantByName(name);
        String restaurantNameById = restaurantService.getRestaurantNameById(restaurantByName);
        assertEquals(name, restaurantNameById);
    }

    @Test
    void getRestaurantNameByIdException() {
        Assertions.assertThrowsExactly(RestaurantNotFoundException.class,
                () -> restaurantService.getRestaurantNameById(333L));
    }

    @Test
    void getTelephoneNumber() throws RestaurantNotFoundException {
        long number = restaurantService.createRestaurantByNameAndTelephone("number", "+79999999999");
        String restaurantTelephone = restaurantService.getRestaurantTelephone(number);
        assertEquals("+79999999999", restaurantTelephone);
        Assertions.assertThrows(RestaurantNotFoundException.class, () -> restaurantService.getRestaurantTelephone(444L));
    }

    @Test
    void createRestaurantByNameAndDateExpirationDate() throws FoundationDateIsExpiredException, RestaurantNotFoundException {
        MockedStatic<LocalDate> localDateMockedStatic = mockStatic(LocalDate.class, CALLS_REAL_METHODS);
        LocalDate defaultNow = LocalDate.of(2014, 12, 22);
        localDateMockedStatic.when(LocalDate::now).thenReturn(defaultNow);

        Assertions.assertThrowsExactly(FoundationDateIsExpiredException.class,
                () -> restaurantService.createRestaurantByNameAndDate("test", LocalDate.of(2015, 12, 12)),
                "Restaurant with name \"" + "test" + "\"" +
                        "has foundation date " + LocalDate.now().plusDays(5));

        long test = restaurantService.createRestaurantByNameAndDate("test", LocalDate.of(2012, 12, 12));
        LocalDate foundationDate = restaurantService.getFoundationDate(test);
        assertEquals(LocalDate.of(2012, 12, 12), foundationDate);
    }

    /**
     * demonstration cascade parameter working
     */
    @Test
    @Transactional
    public void testCascade() {

        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setName("test");
        restaurant.setFoundationDate(LocalDate.now().minusDays(3));
        restaurant.setTelephoneNumber("+79999999999");

        ReviewEntity review1 = new ReviewEntity();
        review1.setReviewText("qqq");
        review1.setRestaurant(restaurant);
        review1.setRating(1);

        ReviewEntity review2 = new ReviewEntity();
        review2.setReviewText("qqq");
        review2.setRestaurant(restaurant);
        review2.setRating(1);

        List<ReviewEntity> list = Lists.list(review1, review2);


        restaurant.setReviews(list);

        RestaurantEntity save = restaurantRepository.save(restaurant);
        Optional<RestaurantEntity> byId = restaurantRepository.findById(save.getId());
        assertTrue(byId.isPresent());
        List<ReviewEntity> all = reviewRepository.findAllByRestaurant(RestaurantEntity.builder().id(byId.get().getId()).build());
        Assertions.assertEquals(2, all.size());
    }

    @BeforeAll
    public void beforeAll() {
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setName("test");
        restaurant.setFoundationDate(LocalDate.now().minusDays(3));
        restaurant.setTelephoneNumber("+79999999999");

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
        restaurantWithReview = restaurantRepository.save(restaurant);
    }

    /**
     * demonstrate n+1 problem. get restaurants in loop - produce n selects (but we can do it with one select).
     */
    @Test
    @Transactional
    public void testN1() {
        List<ReviewEntity> allByRestaurantId = reviewRepository.findAllByRestaurant(RestaurantEntity.builder().id(restaurantWithReview.getId()).build());
        for (ReviewEntity e : allByRestaurantId) {
            System.out.println(e.getRating());
            System.out.println(e.getReviewText());
            System.out.println(e.getRestaurant().getName());
        }
    }

    /**
     * demonstration fetch type. see sql logs to understand difference between fetch type
     * set fetch type in {@link RestaurantEntity} field reviews.
     * @throws RestaurantNotFoundException if restaurant not found
     */
    @Test
    @Transactional
    public void testLazy() throws RestaurantNotFoundException {
        RestaurantEntity restaurant = restaurantService.getRestaurant(restaurantWithReview.getId());
        for (ReviewEntity e : restaurant.getReviews()) {
            System.out.println(e.getRating());
            System.out.println(e.getReviewText());
            System.out.println(e.getRestaurant().getName());
        }
    }
}