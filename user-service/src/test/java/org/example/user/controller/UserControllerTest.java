package org.example.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.example.Application;
import org.example.user.dto.request.UserCreateRequestDTO;
import org.example.user.dto.request.UserUpdateRequestDTO;
import org.example.user.dto.response.UserResponseDTO;
import org.example.user.exception.NonUniqueUserException;
import org.example.user.exception.UserNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {}

    @Test
    @Order(1)
    public void getUserByIdTest() throws Exception {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setNickname("Enryu");
        responseDTO.setEmail("test@gmail.com");
        responseDTO.setInternal(false);
        ObjectMapper objectMapper = new JsonMapper();
        String expected = objectMapper.writeValueAsString(responseDTO);

        mockMvc.perform(get("/users/1"))
                .andDo(print()) //print response in console
                .andExpect(status().isOk()) // check status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // check media typeof response
                .andExpect(content().json(expected)); // check response body

        mockMvc
                .perform(get("/users/{id}", 10L))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof UserNotFoundException))
                .andExpect(content().string("Пользователь с таким id не найден"));
    }


    @Test
    @Order(2)
    void createUserTest() throws Exception {
        UserCreateRequestDTO createRequestDTO = new UserCreateRequestDTO();
        createRequestDTO.setNickname("asd");
        createRequestDTO.setEmail("asd@asd.asd");
        createRequestDTO.setInternal(false);

        //200
        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequestDTO)))
                .andDo(print())
                .andExpect(status().isOk()) // check status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));


        //400
        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequestDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof NonUniqueUserException))
                .andExpect(content().string("Пользователь с таким именем или почтой уже существует"));
    }

    @Test
    @Order(3)
    void updateUserTest() throws Exception {
        UserUpdateRequestDTO updateRequestDto = new UserUpdateRequestDTO();
        updateRequestDto.setId(2L);
        updateRequestDto.setNickname("asd2");
        updateRequestDto.setEmail("asd2@asd.asd");
        updateRequestDto.setInternal(false);

        //200
        this.mockMvc.perform(put("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequestDto)))
                .andDo(print()) //print response in console
                .andExpect(status().isOk()) // check status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        //400
        updateRequestDto.setNickname("Enryu");
        this.mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequestDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof NonUniqueUserException))
                .andExpect(content().string("Пользователь с таким именем или почтой уже существует"));
    }

    @Test
    @Order(4)
    void userDeleteTest() throws Exception {
        this.mockMvc.perform(delete("/users/2")).andExpect(status().isOk());
        this.mockMvc.perform(delete("/users/2")).andExpect(status().isNotFound());
    }

    @Test
    void nonValidEmailTest() throws Exception {
        UserUpdateRequestDTO updateRequestDto = new UserUpdateRequestDTO();
        updateRequestDto.setId(1L);
        updateRequestDto.setNickname("asd");
        updateRequestDto.setEmail("non valid email");
        updateRequestDto.setInternal(false);


        this.mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequestDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
