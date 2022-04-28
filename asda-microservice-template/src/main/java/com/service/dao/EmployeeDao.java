package com.service.dao;

import com.service.exception.EmployeeNotFoundException;
import com.service.model.Employee;
import com.service.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Log4j2
public class EmployeeDao {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> get() {
        try {
            log.info("Fetching data from db");
            return employeeRepository.findAll();
        } catch (Exception e) {
            throw new EmployeeNotFoundException();
        }
    }

    public void save(Employee user) {
        try {
            log.info("Saving data into db");
            employeeRepository.save(user);
        } catch (Exception e) {
            throw new EmployeeNotFoundException();
        }
    }

    public void delete(String empId) {
        try {
            log.info("Deleting employee {} from db", empId);
            employeeRepository.deleteById(empId);
        } catch (Exception e) {
            throw new EmployeeNotFoundException();
        }
    }

    public Optional<Employee> findById(String empId) {
        return employeeRepository.findById(empId);
    }
}
