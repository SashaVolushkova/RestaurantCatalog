package org.example.restaurant.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
public class FoodTypeUpdateInDTO {
    @NotNull
    private final Long id;

    @NotNull
    private final String name;

    private final String description;
}
