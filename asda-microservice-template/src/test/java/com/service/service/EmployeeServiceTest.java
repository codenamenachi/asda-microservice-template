package com.service.service;

import com.service.dao.EmployeeDao;
import com.service.exception.AmbiguousEmpIdException;
import com.service.exception.EmployeeNotFoundException;
import com.service.model.Employee;
import com.service.model.dto.EmployeeDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeDao employeeDao;

    private List<Employee> employeeList;
    private Employee employee;
    private String empId;

    @Before
    public void setup(){
        employee = new Employee();
        employeeList = new ArrayList<>();
        employeeList.add(employee);
        empId = "empId";
    }

    @Test
    public void getEmployeesTest(){
        when(employeeDao.get()).thenReturn(employeeList);
        List<EmployeeDto> result = employeeService.get();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void saveEmployeeTest(){
        EmployeeDto employeeDto = EmployeeDto.builder().build();
        doNothing().when(employeeDao).save(employee);
        employeeService.save(employeeDto);
        verify(employeeDao,times(1)).save(employee);
    }

    @Test
    public void deleteEmployeeTest(){
        doNothing().when(employeeDao).delete(anyString());
        employeeService.delete(empId);
        verify(employeeDao,times(1)).delete(empId);
    }

    @Test
    public void updateEmployeeTest(){
        EmployeeDto employeeDto = EmployeeDto.builder().empId(empId).build();
        when(employeeDao.findById(empId)).thenReturn(Optional.of(employee));
        doNothing().when(employeeDao).save(employee);
        try {
            employeeService.update(empId, employeeDto);
        } catch (AmbiguousEmpIdException e) {
            fail(e.getMessage());
        }
        verify(employeeDao,times(1)).save(employee);
    }

    @Test
    public void updateEmployeeTest_ambiguousEmpId(){
        EmployeeDto employeeDto = EmployeeDto.builder().empId("wrong").build();
        try {
            employeeService.update(empId, employeeDto);
        } catch (AmbiguousEmpIdException e) {
            assertEquals(AmbiguousEmpIdException.class, e.getClass());
        }
    }

    @Test
    public void updateEmployeeTest_employeeNotFound(){
        EmployeeDto employeeDto = EmployeeDto.builder().empId(empId).build();
        when(employeeDao.findById(empId)).thenReturn(Optional.empty());
        try {
            employeeService.update(empId, employeeDto);
        } catch (EmployeeNotFoundException e) {
            assertEquals(EmployeeNotFoundException.class, e.getClass());
        } catch (AmbiguousEmpIdException e) {
            fail(e.getMessage());
        }
    }
}
