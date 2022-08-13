package restaurant.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class ReviewOutDTO {
    private final Long id;
    private final String text;
    private final Integer rate;
}
