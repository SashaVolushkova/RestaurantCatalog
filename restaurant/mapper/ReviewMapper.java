package restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import restaurant.dto.out.ReviewOutDTO;
import restaurant.model.ReviewEntity;


@Mapper(componentModel = "spring")
public interface ReviewMapper {
        ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
        Long toDto(ReviewEntity reviewEntity);
    ReviewOutDTO reviewEntityToReviewDTO(ReviewEntity reviewEntity);

}
