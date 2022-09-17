package org.example.restaurant.service;

import com.google.i18n.phonenumbers.NumberParseException;
import org.example.restaurant.dto.in.RestaurantInDTO;
import org.example.restaurant.exception.FoundationDateIsExpiredException;
import org.example.restaurant.exception.RestaurantNotFoundException;
import org.example.restaurant.model.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ServiceForTest {
    void test8(String name);
    void test9(String name);
    void test10(String name);

    void test11(String name);
}
