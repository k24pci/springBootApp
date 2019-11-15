package com.ucx.training.sessions.app.repository;

import com.ucx.training.sessions.app.entity.Wage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WageRepository extends JpaRepository<Wage, Integer> {
}
