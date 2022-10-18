package org.example.restaurant.service.impl;

import net.bytebuddy.asm.Advice;
import org.example.restaurant.model.RestaurantEntity;
import org.example.restaurant.repository.RestaurantRepository;
import org.example.restaurant.service.ServiceForTest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ServiceForTestImpl implements ServiceForTest {
    private final RestaurantRepository restaurantRepository;
    public ServiceForTestImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
    @Override
    @Transactional
    public void test10(String name) {
        RestaurantEntity firstByName = restaurantRepository.findFirstByName(name);
        firstByName.setName("test10Updated");
    }

    @Override
    @Transactional
    public void test11(String name) {
        RestaurantEntity firstByName = restaurantRepository.findFirstByName(name);
        firstByName.setName("test11Updated");
        if(name.equals("test11")) {
            throw new RuntimeException();
        }
    }

    @Override
    public void test8(String name) {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setName(name);
        restaurantRepository.save(restaurantEntity);
        if(name.equals("test8")) {
            throw new RuntimeException();
        }
    }

    @Override
    public void test9(String name) {
        save(name);
    }

    @Transactional
    public void save(String name) {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setName(name);
        restaurantRepository.save(restaurantEntity);
        if(name.equals("test8")) {
            throw new RuntimeException();
        }
    }

}
