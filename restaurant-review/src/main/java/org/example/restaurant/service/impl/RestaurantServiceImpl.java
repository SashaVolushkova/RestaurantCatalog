package org.example.restaurant.service.impl;

import com.google.i18n.phonenumbers.NumberParseException;
import org.example.restaurant.dto.in.RestaurantInDTO;
import org.example.restaurant.exception.FoundationDateIsExpiredException;
import org.example.restaurant.exception.RestaurantNotFoundException;
import org.example.restaurant.mapper.RestaurantMapper;
import org.example.restaurant.model.RestaurantEntity;
import org.example.restaurant.repository.RestaurantRepository;
import org.example.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository,
                                 RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    public long createRestaurantByName(String name) {
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setName(name);
        RestaurantEntity save = restaurantRepository.save(restaurant);
        return save.getId();
    }

    @Override
    public long createRestaurantByNameAndDate(String name, LocalDate foundationDate) throws FoundationDateIsExpiredException {
        if(foundationDate == null || LocalDate.now().isBefore(foundationDate)) {
            throw new FoundationDateIsExpiredException(name, foundationDate);
        }
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setName(name);
        restaurant.setFoundationDate(foundationDate);
        RestaurantEntity save = restaurantRepository.save(restaurant);
        return save.getId();
    }

    @Override
    public LocalDate getFoundationDate(Long id) throws RestaurantNotFoundException {
        RestaurantEntity restaurantById = getRestaurantById(id);
        return restaurantById.getFoundationDate();
    }

    @Override
    public long createRestaurantByNameAndTelephone(String name, String telephone) {
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setName(name);
        restaurant.setTelephoneNumber(telephone);
        return restaurantRepository.save(restaurant).getId();
    }

    @Override
    public String getRestaurantTelephone(Long id) throws RestaurantNotFoundException {
        RestaurantEntity restaurantById = getRestaurantById(id);
        return restaurantById.getTelephoneNumber();
    }

    @Override
    public RestaurantEntity getRestaurant(Long restaurantId) throws RestaurantNotFoundException {
        return getRestaurantById(restaurantId);
    }

    @Override
    public Page<RestaurantEntity> getRestaurants(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    @Override
    public RestaurantEntity createRestaurant(RestaurantInDTO restaurant) throws NumberParseException, FoundationDateIsExpiredException {
        if(restaurant.getFoundationDate() == null || LocalDate.now().isBefore(restaurant.getFoundationDate())) {
            throw new FoundationDateIsExpiredException(restaurant.getName(), restaurant.getFoundationDate());
        }

        RestaurantEntity restaurantEntity = restaurantMapper.restaurantInDTOToRestaurantEntity(restaurant);
        return restaurantRepository.save(restaurantEntity);
    }

    @Override
    public String getRestaurantNameById(Long id) throws RestaurantNotFoundException {
        return getRestaurantById(id).getName();
    }

    private RestaurantEntity getRestaurantById(Long id) throws RestaurantNotFoundException {
        Optional<RestaurantEntity> byId = restaurantRepository.findById(id);
        if(byId.isEmpty()) {
            throw new RestaurantNotFoundException(id);
        }
        return byId.get();
    }
}
