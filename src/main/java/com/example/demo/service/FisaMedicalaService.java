package com.example.demo.service;

import com.example.demo.dtos.FisaMedicalaResponseDTO;
import com.example.demo.model.FisaMedicala;
import com.example.demo.model.Programari;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface FisaMedicalaService {
    List<Programari> findProgramariCurente(LocalDateTime data);
    String saveFisaMedicala(FisaMedicala fisaMedicala);
    FisaMedicalaResponseDTO findAll();
    FisaMedicalaResponseDTO findAllByCnp(Long cnp);
    FisaMedicala findFisaMedicalaByProgramare(Programari programare);
}