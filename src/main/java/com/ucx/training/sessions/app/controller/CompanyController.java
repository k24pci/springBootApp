package com.ucx.training.sessions.app.controller;

import com.ucx.training.sessions.app.entity.Company;
import com.ucx.training.sessions.app.service.CompanyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is annotated with @RestController which provides RestServices to the outer world (smart phone, javascript app, or some other devices)
 * It communicates with Domain Services to perform a specific task and return response in JSON format
 */
@RestController
@RequestMapping("companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public Map<String, Integer> create(@RequestBody Company company) {
        Map<String, Integer> map = new HashMap<>();
        Integer id = companyService.create(company);
        map.put("Id: ", id);
        return map;
    }
}
