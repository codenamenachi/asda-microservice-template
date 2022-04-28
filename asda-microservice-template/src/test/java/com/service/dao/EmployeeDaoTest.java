package com.service.dao;

import com.service.exception.EmployeeNotFoundException;
import com.service.model.Employee;
import com.service.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeDaoTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeDao employeeDao;

    @Test
    public void saveTest(){
        Employee employee = Employee.builder().build();
        Mockito.doThrow(EmployeeNotFoundException.class).when(employeeRepository).save(employee);
        try {
            employeeDao.save(employee);
        } catch (Exception e) {
            assertEquals(EmployeeNotFoundException.class, e.getClass());
        }
    }

}
