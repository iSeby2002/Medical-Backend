package com.example.demo.service;

import com.example.demo.dtos.PacientDto;
import com.example.demo.model.Pacient;
import org.springframework.stereotype.Component;

@Component
public interface PacientService {
    Pacient registerPacient(PacientDto pacientDto);
    Pacient findPacientByCnp(Long cnp);
}