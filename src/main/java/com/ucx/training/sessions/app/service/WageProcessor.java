package com.ucx.training.sessions.app.service;


import com.ucx.training.sessions.app.entity.Employee;
import com.ucx.training.sessions.app.entity.Wage;
import com.ucx.training.sessions.app.exception.NotFoundException;
import com.ucx.training.sessions.app.exception.WageProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
@Transactional
public class WageProcessor {

    private static final String HEADER = "personalNo,firstName,lastName,hoursWorked,month,year";
    private final EmployeeService employeeService;
    private final WageService wageService;

    public WageProcessor(EmployeeService employeeService, WageService wageService) {
        this.employeeService = employeeService;
        this.wageService = wageService;
    }

    public Map<String, String> calculateWages(InputStream is) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> result = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equalsIgnoreCase(HEADER)) continue;
                String[] parts = line.split(",");
                String personalNo = parts[0].trim();
                Double workingHours = Double.valueOf(parts[3].trim());
                Byte month = Byte.valueOf(parts[4].trim());
                Short year = Short.valueOf(parts[5].trim());
                try {
                    Employee foundEmployee = employeeService.findByPersonalNo(personalNo);
                    Wage wage = new Wage();
                    wage.setEmployee(foundEmployee);
                    wage.setWage(foundEmployee.calculateWage(workingHours));
                    wage.setMonth(month);
                    wage.setYear(year);
                    wageService.create(wage);
                } catch (IllegalArgumentException iex) {
                    log.error(iex.getMessage());
                } catch (NotFoundException nfe) {
                    log.error(nfe.getMessage());
                    log.debug("Continue processing");
                    sb.append(personalNo).append(",");
                } catch (Exception ex) {
                    log.error(ex.getMessage());
                    log.debug("Error occurred while saving wage! Continue with processing next line");
                }
            }
        } catch (IOException ioEx) {
            log.error(ioEx.getMessage());
            throw new WageProcessingException("Error occurred while processing csvFile!");
        }
        result.put("message", sb.length() > 0 ?
                "Wages for personal numbers: " + sb.toString() + "are not processed. The rest processed successfully." :
                "Wages processed successfully!");
        return result;
    }
}