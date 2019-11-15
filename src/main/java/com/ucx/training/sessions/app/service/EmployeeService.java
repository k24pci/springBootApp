package com.ucx.training.sessions.app.service;

import com.ucx.training.sessions.app.entity.Address;
import com.ucx.training.sessions.app.entity.Company;
import com.ucx.training.sessions.app.entity.Employee;
import com.ucx.training.sessions.app.exception.NotFoundException;
import com.ucx.training.sessions.app.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Integer create(Employee employee, Company company){
        employee.setCompany(company);
        employee.getAddress().setEmployee(employee);
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee.getId();
    }

    public Employee findByPersonalNo(String personalNo){
        if (personalNo == null) throw new IllegalArgumentException("Invalid Personal Number!");
        Employee employee = employeeRepository.findByPersonalNo(personalNo);
        if (employee == null) throw new NotFoundException("Employee not found!");
        return employee;
    }
}
