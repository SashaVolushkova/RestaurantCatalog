package restaurant.dto.in;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ReviewInDTO {
    private final String text;
    private final Integer rate;
    @NotNull
    private final Long restaurantId;
}
