package com.ucx.training.sessions.app.service;

import com.ucx.training.sessions.app.entity.Wage;
import com.ucx.training.sessions.app.repository.WageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WageService {
    private final WageRepository wageRepository;

    public WageService(WageRepository wageRepository) {
        this.wageRepository = wageRepository;
    }

    public void create(Wage wage){
        wageRepository.save(wage);
    }
}
