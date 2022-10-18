package org.example.restaurant.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class FoodTypeInDTO {
    private final String name;
    private final String description;
}
