package org.example.restaurant.service.impl;

import org.example.restaurant.exception.RestaurantNotFoundException;
import org.example.restaurant.model.RestaurantEntity;
import org.example.restaurant.repository.RestaurantRepository;
import org.example.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public long createRestaurantByName(String name) {
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setName(name);
        RestaurantEntity save = restaurantRepository.save(restaurant);
        return save.getId();
    }

    @Override
    public String getRestaurantNameById(Long id) throws RestaurantNotFoundException {
        Optional<RestaurantEntity> byId = restaurantRepository.findById(id);
        if(byId.isEmpty()) {
            throw new RestaurantNotFoundException(id);
        }
        return byId.get().getName();
    }
}
