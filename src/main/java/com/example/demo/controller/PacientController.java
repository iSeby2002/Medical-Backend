package com.example.demo.controller;

import com.example.demo.dtos.PacientDto;
import com.example.demo.model.Pacient;
import com.example.demo.service.PacientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/pacienti")
public class PacientController {

    private final PacientService pacientService;

    @Autowired
    public PacientController(PacientService pacientService){
        this.pacientService=pacientService;
    }

    @PostMapping("/register")
    public ResponseEntity<Pacient> register(@RequestBody PacientDto pacientDto){
        Pacient pacient=pacientService.registerPacient(pacientDto);
        return new ResponseEntity<>(pacient, HttpStatus.CREATED);
    }
}