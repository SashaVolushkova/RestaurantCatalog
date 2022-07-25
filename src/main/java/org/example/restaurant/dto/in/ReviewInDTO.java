package org.example.restaurant.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ReviewInDTO {
    private final String text;
    private final Integer rate;
    private final Long restaurantId;
}
