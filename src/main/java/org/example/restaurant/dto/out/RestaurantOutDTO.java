package org.example.restaurant.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class RestaurantOutDTO {
    private final Long id;
    private final String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private final LocalDate foundationDate;
    private final String telephoneNumber;
    private final List<ReviewOutDTO> reviews;
}
