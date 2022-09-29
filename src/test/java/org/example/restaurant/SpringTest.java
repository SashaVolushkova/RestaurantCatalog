package org.example.restaurant;

import org.example.restaurant.abc.A;
import org.example.restaurant.abc.B;
import org.example.restaurant.abc.C;
import org.example.restaurant.model.RestaurantEntity;
import org.example.restaurant.repository.RestaurantRepository;
import org.example.restaurant.service.Service;
import org.example.restaurant.service.ServiceForTest;
import org.example.restaurant.util.AppContextTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpringTest extends AppContextTest {
    @Autowired
    private Service service;

    @Test
    public void test1() {
        /*
         * Какой тип объектов.
         * Подставьте правильное значение в переменные x и y
         */
        Class<?> x = SpringTest.class;
        Class<?> y = SpringTest.class;
        assertInstanceOf(x, service.getObjectReader());
        assertInstanceOf(y, service.getSampleBean());
        /*
         * Опишите причину:
         *
         */
    }

    @Autowired
    private A a;

    @Autowired
    private B b;

    @Autowired
    private C c;
    private static boolean test2_1_finish = false;

    @Test
    @Order(1)
    @DirtiesContext
    public void test2_1() {
        /*
         * Одинаковые ли объекты a и b.getA()?
         * Одинаковые ли объекты b.getA() и c.getA()?
         * Добавьте соответствующие assert
         */

        //assertXXXXXXX(a == b.getA());
        //assertXXXXX(b.getA() == c.getA());

        /*
         * Опишите причину:
         *
         */
        test2_1_finish = true;
    }

    @Test
    @Order(2)
    public void test2_2() {
        /*
         * Сколько раз были вызваны фунции postConstruct и preDestroy?
         * Подставьте правильное значение в переменные x, y, z, w.
         */

        if(test2_1_finish) {
            int x = 9999, y = 9999, z = 9999, w = 9999;
            assertEquals(x, A.postConstructCount.get());
            assertEquals(y, A.preDestroyCount.get());
            assertEquals(z, B.postConstructCount.get());
            assertEquals(w, B.preDestroyCount.get());
        } else {
            int x = 9999, y = 9999, z = 9999, w = 9999;
            assertEquals(x, A.postConstructCount.get());
            assertEquals(y, A.preDestroyCount.get());
            assertEquals(z, B.postConstructCount.get());
            assertEquals(w, B.preDestroyCount.get());
        }
        /*
         * Опишите причину:
         *
         * На что влияет @DirtiesContext?:
         *
         */
    }

    @Autowired
    ServiceForTest serviceForTest;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    public void test8() {
        try {
            serviceForTest.test8("test8");
        } catch (RuntimeException e) {
            System.out.println("error");
        }
        RestaurantEntity byId = restaurantRepository.findFirstByName("test8");
        /*
         * Будет ли сохранен test8 в методе test8?
         * Добавьте соответствующий assert
         */

        // assertXXXXXXX(byId);
    }
    @Test
    public void test9() {
        try {
            serviceForTest.test9("test8");
        } catch (RuntimeException e) {
            System.out.println("error");
        }
        RestaurantEntity byId = restaurantRepository.findFirstByName("test8");

        /*
         * Будет ли сохранен test9 в методе test8?
         * Добавьте соответствующий assert
         */

        // assertXXXXXXX(byId);
    }
    @Test
    public void test10() {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setName("test10");
        restaurantRepository.save(restaurantEntity);
        serviceForTest.test10("test10");
        Optional<RestaurantEntity> byId = restaurantRepository.findById(restaurantEntity.getId());
        assertTrue(byId.isPresent());
        /*
         * Будет ли изменен test10 в методе test10?
         * Присвойте перенной x нужное значение
         */
        String x = "not valid string";
        assertEquals(x, byId.get().getName());
    }

    @Test
    public void test11() {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setName("test11");
        restaurantRepository.save(restaurantEntity);
        serviceForTest.test11("test11");
        Optional<RestaurantEntity> byId = restaurantRepository.findById(restaurantEntity.getId());
        assertTrue(byId.isPresent());
        /*
         * Будет ли изменен test11 в методе test11?
         * Присвойте перенной x нужное значение
         */
        String x = "not valid string";
        assertEquals(x, byId.get().getName());
    }
}
