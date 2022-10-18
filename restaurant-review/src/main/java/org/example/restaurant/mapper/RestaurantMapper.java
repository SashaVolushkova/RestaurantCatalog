package org.example.restaurant.mapper;

import com.google.i18n.phonenumbers.NumberParseException;
import org.example.restaurant.dto.in.RestaurantInDTO;
import org.example.restaurant.dto.out.RestaurantOutDTO;
import org.example.restaurant.dto.out.ReviewOutDTO;
import org.example.restaurant.model.RestaurantEntity;
import org.example.restaurant.model.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class RestaurantMapper {
    @Autowired
    private ReviewMapper reviewMapper;

    public abstract RestaurantOutDTO restaurantEntityToRestaurantOutDTO(RestaurantEntity restaurant);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "telephoneNumber",
            expression = "java(org.example.restaurant.util.Util.reformatRuTelephone(restaurant.getTelephoneNumber()))"
    )
    public abstract RestaurantEntity restaurantInDTOToRestaurantEntity(RestaurantInDTO restaurant) throws NumberParseException;
    ReviewOutDTO reviewEntityToReviewOutDTO(ReviewEntity reviewEntity) {
        return reviewMapper.reviewEntityToReviewDTO(reviewEntity);
    }
}
