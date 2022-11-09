package org.example.employee.controller;


import org.example.employee.dto.request.DepartmentRequestDTO;
import org.example.employee.dto.response.DepartmentResponseDTO;
import org.example.employee.dto.response.EmployeeResponseDTO;
import org.example.employee.error.NotFoundRecordException;
import org.example.employee.service.EmployeeService;
import org.example.employee.util.AppContextTest;
import org.example.employee.util.TestUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

import static net.javacrumbs.jsonunit.spring.JsonUnitResultMatchers.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Sql(scripts = "/data/insert_test_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(encoding = "utf-8"))
@Sql(scripts = "/data/clear_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(encoding = "utf-8"))
public class DepartmentControllerTest extends AppContextTest {

    private static final Locale LOCALE_RU = new Locale("ru", "RU");

    private static final String BASE_REQUEST = "json/request/";
    private static final String BASE_RESPONSE = "json/response/";

    private static final String REQUEST_CREATE_SUCCESS = BASE_REQUEST + "department_create_success.json";
    private static final String REQUEST_CREATE_EXCEPTION = BASE_REQUEST + "department_create_exception.json";
    private static final String REQUEST_UPDATE_SUCCESS = BASE_REQUEST + "department_update_success.json";
    private static final String REQUEST_UPDATE_EXCEPTION = BASE_REQUEST + "department_update_exception.json";

    private static final String RESPONSE_GET_ID = BASE_RESPONSE + "department_get_id.json";
    private static final String RESPONSE_GET_ALL = BASE_RESPONSE + "departments_get_all.json";
    private static final String RESPONSE_CREATE_SUCCESS = BASE_RESPONSE + "department_create_success.json";
    private static final String RESPONSE_UPDATE_SUCCESS = BASE_RESPONSE + "department_update_success.json";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeService employeeService;

    /**
     * должен выполнятьcя первым
     * @throws Exception exception
     */
    @Test
    void getDepartmentsTest() throws Exception {
        final List<DepartmentResponseDTO> response =
                TestUtil.readJsonResourceToList(RESPONSE_GET_ALL, DepartmentResponseDTO.class);
        final String expected = TestUtil.write(response);
        this.mockMvc
                .perform(get("/departmens"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
    }

    @Test
    void getDepartmentByIdSuccessTest() throws Exception {
        final DepartmentResponseDTO response =
                TestUtil.readJsonResource(RESPONSE_GET_ID, DepartmentResponseDTO.class);
        final String expected = TestUtil.write(response);

        this.mockMvc
                .perform(get("/departmens/{id}", 1L))
                        .andDo(print()).andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(content().json(expected));
    }

    @Test
    void getDepartmentByIdExceptionTest() throws Exception {
        final String expected = "В таблице: department не найдена запись с идентификатором: 3";

        this.mockMvc
                .perform(get("/departmens/{id}", 3L))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof NotFoundRecordException))
                .andExpect(content().string(expected));
    }

    @Test
    void deleteDepartmentSuccessTest() throws Exception {
        List<EmployeeResponseDTO> employees = employeeService.getEmployees();
        Assertions.assertEquals(2, employees.size());
        /*
        Отдел удаляется вместе с сотрудниками отдела
         */
        this.mockMvc
                .perform(delete("/departmens/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk());
        employees = employeeService.getEmployees();
        Assertions.assertEquals(1, employees.size());
        Assertions.assertEquals(2L, employees.get(0).getDepartmentId());
    }

    @Test
    void deleteDepartmentExceptionTest() throws Exception {
        final String expected = "В таблице: department не найдена запись с идентификатором: 3";

        this.mockMvc
                .perform(delete("/departmens/{id}", 3L))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof NotFoundRecordException))
                .andExpect(content().string(expected));
    }

    @Test
    void createDepartmentSuccessTest() throws Exception {
        InputStream inputStream = TestUtil.readResource(RESPONSE_CREATE_SUCCESS);
        final byte[] requestBytes = TestUtil.readResource(REQUEST_CREATE_SUCCESS).readAllBytes();
        final String expected = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        /*
        samples https://github.com/lukas-krecan/JsonUnit/blob/master/json-unit-spring/src/test/java/net/javacrumbs/jsonunit/spring/testit/MockMvcTest.java
         */
        this.mockMvc
                .perform(post("/departmens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBytes)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(json().ignoring("##IGNORE##").isEqualTo(expected));
    }

    @Test
    void createDepartmentExceptionTest() throws Exception {
        final byte[] requestBytes =
                TestUtil.readResource(REQUEST_CREATE_EXCEPTION).readAllBytes();
        final String json = "{\"id\":\"должно равняться null\",\"name\":\"должно соответствовать \\\"^[а-яА-ЯёЁ ]+\\\"\"}";

        this.mockMvc
                .perform(post("/departmens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBytes)
                        .locale(LOCALE_RU)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(json));
    }

    @Test
    void updateDepartmentSuccessTest() throws Exception {
        final DepartmentRequestDTO request =
                TestUtil.readJsonResource(REQUEST_UPDATE_SUCCESS, DepartmentRequestDTO.class);
        final DepartmentResponseDTO response =
                TestUtil.readJsonResource(RESPONSE_UPDATE_SUCCESS, DepartmentResponseDTO.class);
        final byte[] requestBytes = TestUtil.readResource(REQUEST_UPDATE_SUCCESS).readAllBytes();
        final String expected = TestUtil.write(response);

        this.mockMvc
                .perform(put("/departmens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBytes)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(content().string(expected));
    }

    @Test
    void updateDepartmentExceptionTest() throws Exception {
        final byte[] requestBytes =
                TestUtil.readResource(REQUEST_UPDATE_EXCEPTION).readAllBytes();
        final String json = "{\"id\":\"не должно равняться null\"}";

        this.mockMvc
                .perform(put("/departmens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBytes)
                        .locale(LOCALE_RU)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(json));
    }

}
