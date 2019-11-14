package com.ucx.training.sessions.app;

import com.ucx.training.sessions.app.entity.Address;
import com.ucx.training.sessions.app.entity.Company;
import com.ucx.training.sessions.app.entity.Employee;
import com.ucx.training.sessions.app.type.Position;

import java.util.List;

public class MockData {
    private MockData(){
    }

    public static List<Company> getCompanies(){
        Company company1 = new Company();
        company1.setName("UCX LLC");

        Company company2 = new Company();
        company2.setName("C2 LLC");

        return List.of(company1, company2);
    }

    public static Employee getEmployee(){
        Employee employee = new Employee();
        employee.setPersonalNo("11221100800");
        employee.setPosition(Position.DEV);
        Address address = new Address();
        address.setCountry("Kosove");
        address.setCity("Prishtine");
        address.setZipCode(10000);
        employee.setAddress(address);
        return employee;
    }
}
