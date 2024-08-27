package com.example.demo.controller;

import com.example.demo.dtos.LoginDto;
import com.example.demo.dtos.LoginResponseDTO;
import com.example.demo.service.MedicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/medici")
public class MedicController {

    private final MedicService medicService;

    @Autowired
    public MedicController(MedicService medicService)
    {
        this.medicService=medicService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDto loginDto)
    {
        LoginResponseDTO loginResponseDTO = medicService.logIn(loginDto);
        if(loginResponseDTO.getMesaj().equals("Logare cu succes!")) {
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.BAD_REQUEST);
        }
    }
}