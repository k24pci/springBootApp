package com.ucx.training.sessions.app.repository;

import com.ucx.training.sessions.app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
