package com.example.demo.service.impl;

import com.example.demo.dtos.PacientDto;
import com.example.demo.model.FisaMedicala;
import com.example.demo.model.Pacient;
import com.example.demo.model.Programari;
import com.example.demo.repository.FisaMedicalaRepository;
import com.example.demo.repository.ProgramariRepository;
import com.example.demo.service.PacientService;
import com.example.demo.service.ProgramariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class ProgramariServiceImpl implements ProgramariService{

    private final ProgramariRepository programariRepository;
    private final PacientService pacientService;
    private final FisaMedicalaRepository fisaMedicalaRepository;

    @Autowired
    public ProgramariServiceImpl(ProgramariRepository programariRepository, PacientService pacientService, FisaMedicalaRepository fisaMedicalaRepository) {
        this.programariRepository = programariRepository;
        this.pacientService = pacientService;
        this.fisaMedicalaRepository = fisaMedicalaRepository;
    }

    @Override
    public String saveProgramare(Programari programare) {
        PacientDto pacientDto = PacientDto.builder()
                .numePrenume(programare.getPacient().getNumePrenume())
                .cnp(programare.getPacient().getCnp())
                .build();
        Pacient pacientCautat = pacientService.findPacientByCnp(programare.getPacient().getCnp());

        LocalDateTime startOfDay = programare.getStartTime().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusSeconds(1);
        int countProgramariByDay = programariRepository.countProgramariByDay(startOfDay, endOfDay);

        if(countProgramariByDay < 5){
            if (pacientCautat == null) {
                Programari programareExistenta = programariRepository.findProgramariByStartTime(programare.getStartTime());
                if(programareExistenta == null) {
                    Pacient savedPacient = pacientService.registerPacient(pacientDto);
                    Programari savedProgramare = Programari.builder()
                            .pacient(savedPacient)
                            .startTime(programare.getStartTime())
                            .build();
                    programariRepository.save(savedProgramare);
                }else{
                    return "Există deja o programare la această oră!";
                }
            }else {
                // Verifică dacă există deja o programare în aceeași săptămână
                LocalDateTime startTime = programare.getStartTime();
                LocalDate startOfWeek = startTime.toLocalDate().with(java.time.DayOfWeek.MONDAY);
                LocalDate endOfWeek = startOfWeek.plusDays(6);

                List<Programari> programariInAceeasiSaptamana = programariRepository.findAllByPacientAndStartTimeBetween(
                        pacientCautat, startOfWeek.atStartOfDay(), endOfWeek.atTime(23, 59));

                if (!programariInAceeasiSaptamana.isEmpty()) {
                    Programari programareExistenta = programariRepository.findProgramariByStartTime(programare.getStartTime());
                    if(programareExistenta == null) {
                        Programari savedProgramare = Programari.builder()
                                .pacient(pacientCautat)
                                .startTime(programare.getStartTime())
                                .build();
                        programariRepository.save(savedProgramare);
                        return "Avertizare! Pacientul are deja o programare în această săptămână!";
                    }else{
                        return "Există deja o programare la această oră!";
                    }
                }else{
                    Programari programareExistenta = programariRepository.findProgramariByStartTime(programare.getStartTime());
                    if(programareExistenta == null) {
                        Programari savedProgramare = Programari.builder()
                                .pacient(pacientCautat)
                                .startTime(programare.getStartTime())
                                .build();
                        programariRepository.save(savedProgramare);
                    }else{
                        return "Există deja o programare la această oră!";
                    }
                }
            }
            return "Programare reușită";
        }else{
            return "S-a atins numărul maxim de programări posibile pe ziua selectată!";
        }
    }

    @Override
    public String updateProgramare(Programari programareUpdate) {
        Programari programare = programariRepository.findProgramariByStartTime(programareUpdate.getStartTime());
        if (programare == null) {
            return "Nu există programarea selectată!";
        } else if(programare.getPacient().getCnp() == programareUpdate.getPacient().getCnp()) {
            return "Este același pacient cu cel din programarea selectată!";
        } else {
            FisaMedicala fisaMedicala=fisaMedicalaRepository.findFisaMedicalaByProgramari(programare);
            if(fisaMedicala!=null) {
                fisaMedicalaRepository.delete(fisaMedicala);
            }
            Pacient pacientExistent = pacientService.findPacientByCnp(programareUpdate.getPacient().getCnp());
            if (pacientExistent == null) {
                PacientDto pacientDto = PacientDto.builder()
                        .numePrenume(programareUpdate.getPacient().getNumePrenume())
                        .cnp(programareUpdate.getPacient().getCnp())
                        .build();
                Pacient registerPacient = pacientService.registerPacient(pacientDto);
                programare.setPacient(registerPacient);
            } else {
                programare.setPacient(pacientExistent);

                // Verifică dacă există deja o programare în aceeași săptămână
                LocalDateTime startTime = programare.getStartTime();
                LocalDate startOfWeek = startTime.toLocalDate().with(java.time.DayOfWeek.MONDAY);
                LocalDate endOfWeek = startOfWeek.plusDays(6);

                List<Programari> programariInAceeasiSaptamana = programariRepository.findAllByPacientAndStartTimeBetween(
                        pacientExistent, startOfWeek.atStartOfDay(), endOfWeek.atTime(23, 59));

                if (!programariInAceeasiSaptamana.isEmpty()) {
                    programariRepository.save(programare);
                    return "Avertizare! Pacientul are deja o programare în această săptămână!";
                }
            }
            programariRepository.save(programare);
            return "Editare programare realizată cu succes!";
        }
    }

    @Override
    public String deleteProgramare(Programari programareDelete) {
        Programari programare= programariRepository.findProgramariByStartTime(programareDelete.getStartTime());
        if(programare == null){
            return "Nu există programarea selectată!";
        }else {
            FisaMedicala fisaMedicala=fisaMedicalaRepository.findFisaMedicalaByProgramari(programare);
            if(fisaMedicala!=null) {
                fisaMedicalaRepository.delete(fisaMedicala);
            }
            programariRepository.delete(programare);
            return "Anulare programare realizată cu succes!";
        }
    }

    @Override
    public List<Programari> getProgramariByWeek(LocalDate startWeek) {
        // Calculăm începutul și sfârșitul săptămânii
        LocalDateTime startOfWeek = startWeek.atStartOfDay();
        LocalDateTime endOfWeek = startWeek.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY)).atTime(LocalTime.MAX);

        // Apelăm metoda din repository pentru a obține programările
        return programariRepository.findProgramariByWeek(startOfWeek, endOfWeek);
    }
}