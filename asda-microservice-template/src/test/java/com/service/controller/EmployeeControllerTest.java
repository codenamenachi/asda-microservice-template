package com.service.controller;

import com.service.exception.AmbiguousEmpIdException;
import com.service.model.dto.EmployeeDto;
import com.service.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    private HttpHeaders headers;

    @Before
    public void setup(){
        headers = new HttpHeaders();
        headers.add("correlation-id", "corr");
        headers.add("session-id", "sess");
        headers.add("locale", "en-US");
        headers.add("country-code", "UK");
    }

    @Test
    public void createTest(){
        ResponseEntity<?> response = employeeController.create(EmployeeDto.builder().build());
        Assertions.assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateTest(){
        ResponseEntity<?> response = null;
        try {
            response = employeeController.updateEmployeeDetails("empId", EmployeeDto.builder().empId("empId").build());
        } catch (AmbiguousEmpIdException e) {
            fail(e.getMessage());
        }
        Assertions.assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteTest(){
        ResponseEntity<?> response = null;
        response = employeeController.delete("empId");
        Assertions.assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
