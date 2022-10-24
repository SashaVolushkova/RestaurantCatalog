package org.example.employee.controller;

import org.example.employee.dto.request.EmployeeRequestDTO;
import org.example.employee.dto.response.EmployeeResponseDTO;
import org.example.employee.error.ErrorCode;
import org.example.employee.error.MultiValidationException;
import org.example.employee.error.NotFoundRecordException;
import org.example.employee.error.ValidationException;
import org.example.employee.service.EmployeeService;
import org.example.employee.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    private static final String BASE_REQUEST = "json/request/";
    private static final String BASE_RESPONSE = "json/response/";

    private static final String REQUEST_CREATE_1 = BASE_REQUEST + "employee_create_1.json";
    private static final String REQUEST_CREATE_2 = BASE_REQUEST + "employee_create_2.json";
    private static final String REQUEST_UPDATE_1 = BASE_REQUEST + "employee_update_1.json";
    private static final String REQUEST_UPDATE_2 = BASE_REQUEST + "employee_update_2.json";

    private static final String RESPONSE_GET_1 = BASE_RESPONSE + "employee_get_1.json";
    private static final String RESPONSE_GET_2 = BASE_RESPONSE + "employee_get_2.json";
    private static final String RESPONSE_CREATE_1 = BASE_RESPONSE + "employee_create_1.json";
    private static final String RESPONSE_UPDATE_1 = BASE_RESPONSE + "employee_update_1.json";

    @MockBean
    private EmployeeService employeeService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getEmployeeByIdSuccessTest() throws Exception {
        final EmployeeResponseDTO response =
                TestUtil.readJsonResource(RESPONSE_GET_1, EmployeeResponseDTO.class);
        final String expected = TestUtil.write(response);

        doReturn(response)
                .when(employeeService)
                .getEmployeeById(2L);

        this.mockMvc
                .perform(get("/employees/{id}", 2L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
    }

    @Test
    void getEmployeeByIdExceptionTest() throws Exception {
        final String expected = "В таблице: employee не найнеда запись с идентификатором: 3";
        doThrow(new NotFoundRecordException(new Object[]{"employee", "3"}))
                .when(employeeService)
                .getEmployeeById(3L);

        this.mockMvc
                .perform(get("/employees/{id}", 3L))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof NotFoundRecordException))
                .andExpect(content().string(expected));
    }

    @Test
    void getEmployeesTest() throws Exception {
        final List<EmployeeResponseDTO> response =
                TestUtil.readJsonResourceToList(RESPONSE_GET_2, EmployeeResponseDTO.class);
        final String expected = TestUtil.write(response);

        doReturn(response)
                .when(employeeService)
                .getEmployees();

        this.mockMvc
                .perform(get("/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
    }

    @Test
    void deleteEmployeeSuccessTest() throws Exception {
        this.mockMvc
                .perform(delete("/employees/{id}", 2L))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteEmployeeExceptionTest() throws Exception {
        final String expected = "В таблице: employee не найнеда запись с идентификатором: 3";
        doThrow(new NotFoundRecordException(new Object[]{"employee", "3"}))
                .when(employeeService)
                .deleteEmployee(3l);

        this.mockMvc
                .perform(delete("/employees/{id}", 3L))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof NotFoundRecordException))
                .andExpect(content().string(expected));
    }

    @Test
    void createEmployeeSuccessTest() throws Exception {
        final EmployeeRequestDTO request =
                TestUtil.readJsonResource(REQUEST_CREATE_1, EmployeeRequestDTO.class);
        final EmployeeResponseDTO response =
                TestUtil.readJsonResource(RESPONSE_CREATE_1, EmployeeResponseDTO.class);
        final byte[] requestBytes = TestUtil.readResource(REQUEST_CREATE_1).readAllBytes();
        final String expected = TestUtil.write(response);
        doReturn(response)
                .when(employeeService)
                .createEmployee(eq(request));

        this.mockMvc
                .perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBytes)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(content().string(expected));
    }

    @Test
    void createEmployeeExceptionTest() throws Exception {
        final EmployeeRequestDTO request =
                TestUtil.readJsonResource(REQUEST_CREATE_2, EmployeeRequestDTO.class);
        final byte[] requestBytes =
                TestUtil.readResource(REQUEST_CREATE_2).readAllBytes();
        final String expected = "В представлении лишний атрибут: id;\n" +
                "Неверный формат атрибута: name;\n" +
                "Неверный формат атрибута: email;\n" +
                "Отсутствует обязательный атрибут: departmentId";
        doThrow(new MultiValidationException(List.of(
                        new ValidationException(ErrorCode.NOT_NULL_ATTRIBUTE, new Object[]{"id"}),
                        new ValidationException(ErrorCode.INVALID_ATTRIBUTE_FORM, new Object[]{"name"}),
                        new ValidationException(ErrorCode.INVALID_ATTRIBUTE_FORM, new Object[]{"email"}),
                        new ValidationException(ErrorCode.MISSING_REQUIRED_ATTRIBUTE, new Object[]{"departmentId"})
                )
                )
        )
                .when(employeeService)
                .createEmployee(eq(request));

        this.mockMvc
                .perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBytes)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MultiValidationException))
                .andExpect(content().string(expected));
    }

    @Test
    void updateEmployeeSuccessTest() throws Exception {
        final EmployeeRequestDTO request =
                TestUtil.readJsonResource(REQUEST_UPDATE_1, EmployeeRequestDTO.class);
        final EmployeeResponseDTO response =
                TestUtil.readJsonResource(RESPONSE_UPDATE_1, EmployeeResponseDTO.class);
        final byte[] requestBytes = TestUtil.readResource(REQUEST_UPDATE_1).readAllBytes();
        final String expected = TestUtil.write(response);
        doReturn(response)
                .when(employeeService)
                .updateEmployee(eq(request));

        this.mockMvc
                .perform(put("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBytes)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(content().string(expected));
    }

    @Test
    void updateEmployeeExceptionTest() throws Exception {
        final EmployeeRequestDTO request =
                TestUtil.readJsonResource(REQUEST_UPDATE_2, EmployeeRequestDTO.class);
        final byte[] requestBytes =
                TestUtil.readResource(REQUEST_UPDATE_2).readAllBytes();
        final String expected = "Отсутствует обязательный атрибут: id;\n" +
                "Отсутствует обязательный атрибут: salary";
        doThrow(new MultiValidationException(List.of(
                        new ValidationException(ErrorCode.MISSING_REQUIRED_ATTRIBUTE, new Object[]{"id"}),
                        new ValidationException(ErrorCode.MISSING_REQUIRED_ATTRIBUTE, new Object[]{"salary"})
                )
                )
        )
                .when(employeeService)
                .updateEmployee(eq(request));

        this.mockMvc
                .perform(put("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBytes)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MultiValidationException))
                .andExpect(content().string(expected));
    }
}
