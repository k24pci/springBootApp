package com.ucx.training.sessions.app.repository;

import com.ucx.training.sessions.app.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface extends JpaRepository and it's used to perform CRUD and other DB operations
 * The interface is injected and used by Domain Service
 */
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
