package com.service.service;

import com.service.dao.EmployeeDao;
import com.service.exception.AmbiguousEmpIdException;
import com.service.exception.EmployeeNotFoundException;
import com.service.model.Employee;
import com.service.model.dto.EmployeeDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<EmployeeDto> get() {

        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDao.get().forEach(employee ->  {
            EmployeeDto employeeDto = EmployeeDto.builder().build();
            BeanUtils.copyProperties(employee, employeeDto);
            employeeDtoList.add(employeeDto);
        });
        return employeeDtoList;
    }

    public void save(EmployeeDto user) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(user, employee);
        employeeDao.save(employee);
    }

    public void delete(String empId) {
        employeeDao.delete(empId);
    }

    public void update(String empId, EmployeeDto newEmployeeDetails) throws AmbiguousEmpIdException {
        if(!empId.equals(newEmployeeDetails.getEmpId()))
            throw new AmbiguousEmpIdException();
        Optional<Employee> optEmployee = employeeDao.findById(empId);
        if(!optEmployee.isPresent()){
            throw new EmployeeNotFoundException();
        }
        Employee employee = optEmployee.get();
        BeanUtils.copyProperties(newEmployeeDetails, employee);
        employeeDao.save(employee);
    }
}
