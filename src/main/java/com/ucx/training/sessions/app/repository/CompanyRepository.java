package com.ucx.training.sessions.app.repository;

import com.ucx.training.sessions.app.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
