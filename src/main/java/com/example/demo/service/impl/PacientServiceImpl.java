package com.example.demo.service.impl;

import com.example.demo.dtos.PacientDto;
import com.example.demo.model.Pacient;
import com.example.demo.repository.PacientRepository;
import com.example.demo.service.PacientService;
import org.springframework.stereotype.Service;

@Service
public class PacientServiceImpl implements PacientService {

    private final PacientRepository pacientRepository;

    public PacientServiceImpl(PacientRepository pacientRepository) {
        this.pacientRepository = pacientRepository;
    }

    @Override
    public Pacient registerPacient(PacientDto pacientDto) {
        Pacient pacient = Pacient.builder()
                .numePrenume(pacientDto.getNumePrenume())
                .cnp(pacientDto.getCnp())
                .build();
        return pacientRepository.save(pacient);
    }

    @Override
    public Pacient findPacientByCnp(Long cnp){
        Pacient pacientCautat = pacientRepository.findPacientByCnp(cnp);
        if(pacientCautat!=null){
            return pacientCautat;
        }
        return null;
    }
}