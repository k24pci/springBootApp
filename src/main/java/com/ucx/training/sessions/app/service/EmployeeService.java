package com.ucx.training.sessions.app.service;

import com.ucx.training.sessions.app.entity.Address;
import com.ucx.training.sessions.app.entity.Company;
import com.ucx.training.sessions.app.entity.Employee;
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
}
