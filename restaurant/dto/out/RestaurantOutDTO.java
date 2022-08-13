package restaurant.dto.out;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class RestaurantOutDTO {
    private final Long id;
    private final String name;

    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private final LocalDate foundationDate;
    private final String telephoneNumber;
    private final List<ReviewOutDTO> reviews;
}
