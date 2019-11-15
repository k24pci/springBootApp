package com.ucx.training.sessions.app.controller;

import com.ucx.training.sessions.app.service.WageProcessor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RestController
@RequestMapping("companies")
public class WageController {
    private final WageProcessor wageProcessor;

    public WageController(WageProcessor wageProcessor) {
        this.wageProcessor = wageProcessor;
    }

    @PostMapping("{id}/employees/wages")
    public ResponseEntity<Void> calculateWages(@RequestParam("csvFile") MultipartFile csvFile, @PathVariable Integer id){
        try {
            wageProcessor.calculateWages(csvFile.getInputStream());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
