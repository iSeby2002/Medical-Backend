package com.example.demo.service;

import com.example.demo.dtos.*;
import com.example.demo.model.Medic;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MedicService {
    LoginResponseDTO logIn(LoginDto loginDto);
    RegisterResponseDTO register(RegisterDto registerDto);
    List<Medic> getMedici();
    UpdateResponseDTO update(Medic medic);
    UpdateResponseDTO schimbareParola(Medic medicUpdate);
    String  delete(Medic medic);
}