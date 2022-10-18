package org.example.restaurant.mapper;

import org.example.restaurant.dto.out.ReviewOutDTO;
import org.example.restaurant.model.ReviewEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mappings({
            @Mapping(source = "rating", target = "rate"),
            @Mapping(source = "reviewText", target = "text")
    })
    ReviewOutDTO reviewEntityToReviewDTO(ReviewEntity reviewEntity);
}
