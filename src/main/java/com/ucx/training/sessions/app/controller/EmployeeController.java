package com.ucx.training.sessions.app.controller;

import com.ucx.training.sessions.app.entity.Company;
import com.ucx.training.sessions.app.entity.Employee;
import com.ucx.training.sessions.app.service.CompanyService;
import com.ucx.training.sessions.app.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("companies")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final CompanyService companyService;

    public EmployeeController(EmployeeService employeeService, CompanyService companyService) {
        this.employeeService = employeeService;
        this.companyService = companyService;
    }

    @PostMapping("{id}/employees")
    public Map<String,Integer> create(@RequestBody Employee employee, @PathVariable Integer id){
        Map<String, Integer> result = new HashMap<>();
        Company foundCompany = companyService.findById(id);
        Integer employeeId = employeeService.create(employee,foundCompany);
        result.put("id", employeeId);
        return result;
    }
}
