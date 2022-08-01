package org.example.restaurant.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Builder
public class ReviewInDTO {
    private final String text;
    private final Integer rate;
    @NotNull
    private final Long restaurantId;
}
