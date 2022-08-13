package restaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import restaurant.dto.in.RestaurantInDTO;
import restaurant.dto.out.RestaurantOutDTO;
import restaurant.model.RestaurantEntity;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);
    RestaurantOutDTO toDto(RestaurantEntity restaurantEntity);

    @Mapping(source = "restaurant.id", target = "id")
    @Mapping(source = "restaurant.name", target = "name")
    @Mapping(source = "restaurant.telephone_number", target = "telephone_number")

    RestaurantOutDTO restaurantEntityToRestaurantOutDTO(RestaurantEntity restaurantEntity);
    RestaurantEntity restaurantInDTOToRestaurantEntity(RestaurantInDTO restaurant);
}
