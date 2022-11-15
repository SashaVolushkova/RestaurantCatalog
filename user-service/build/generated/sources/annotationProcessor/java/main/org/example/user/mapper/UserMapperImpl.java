package org.example.user.mapper;

import javax.annotation.processing.Generated;
import org.example.user.dto.request.UserCreateRequestDTO;
import org.example.user.dto.request.UserUpdateRequestDTO;
import org.example.user.dto.response.UserResponseDTO;
import org.example.user.model.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-15T13:39:29+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId( user.getId() );
        userResponseDTO.setNickname( user.getNickname() );
        userResponseDTO.setEmail( user.getEmail() );
        userResponseDTO.setInternal( user.isInternal() );

        return userResponseDTO;
    }

    @Override
    public User toEntityFromCreateRequestDTO(UserCreateRequestDTO requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        User user = new User();

        user.setNickname( requestDto.getNickname() );
        user.setEmail( requestDto.getEmail() );
        user.setInternal( requestDto.isInternal() );

        return user;
    }

    @Override
    public User toEntityFromUpdateRequestDTO(UserUpdateRequestDTO requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( requestDto.getId() );
        user.setNickname( requestDto.getNickname() );
        user.setEmail( requestDto.getEmail() );
        user.setInternal( requestDto.isInternal() );

        return user;
    }
}
