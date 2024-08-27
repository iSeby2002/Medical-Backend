package com.example.demo.controller;

import com.example.demo.dtos.RegisterDto;
import com.example.demo.dtos.RegisterResponseDTO;
import com.example.demo.dtos.UpdateResponseDTO;
import com.example.demo.model.Medic;
import com.example.demo.service.MedicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    private final MedicService medicService;

    @Autowired
    public AdminController(MedicService medicService) {
        this.medicService = medicService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterDto registerDto){
        RegisterResponseDTO registerResponseDTO = medicService.register(registerDto);
        if(registerResponseDTO.getMesaj().equals("Înregistrare cu succes!")) {
            return new ResponseEntity<>(registerResponseDTO, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(registerResponseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getMedici")
    public ResponseEntity<List<Medic>> getMedici() {
        List<Medic> medici = medicService.getMedici();
        return new ResponseEntity<>(medici, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateResponseDTO> update(@RequestBody Medic medic) {
        UpdateResponseDTO updateResponseDTO = medicService.update(medic);
        if(updateResponseDTO.getMesaj().equals("Actualizare reușită!")){
            return new ResponseEntity<>(updateResponseDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(updateResponseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/schimbareParola")
    public ResponseEntity<UpdateResponseDTO> schimbareParola(@RequestBody Medic medic) {
        UpdateResponseDTO updateResponseDTO = medicService.schimbareParola(medic);
        if(updateResponseDTO.getMesaj().equals("Schimbare parolă reușită!")){
            return new ResponseEntity<>(updateResponseDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(updateResponseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Medic medic) {
        String mesaj = medicService.delete(medic);
        if(mesaj.equals("Ștergere reușită!")){
            return new ResponseEntity<>(mesaj, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(mesaj, HttpStatus.BAD_REQUEST);
        }
    }
}