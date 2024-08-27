package com.example.demo.service.impl;

import com.example.demo.model.SlotBlocat;
import com.example.demo.repository.SlotBlocatRepository;
import com.example.demo.service.SlotBlocatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class SlotBlocatServiceImpl implements SlotBlocatService {

    private final SlotBlocatRepository slotBlocatRepository;

    @Autowired
    public SlotBlocatServiceImpl(SlotBlocatRepository slotBlocatRepository) {
        this.slotBlocatRepository = slotBlocatRepository;
    }

    @Override
    public String saveSlotBlocat(LocalDateTime slot) {
        SlotBlocat slotBlocatExistent = slotBlocatRepository.findSlotBlocatBySlot(slot);
        if(slotBlocatExistent != null) {
            return "Acest slot este deja blocat!";
        }else {
            SlotBlocat slotBlocat = SlotBlocat.builder()
                    .slot(slot)
                    .build();
            slotBlocatRepository.save(slotBlocat);
            return "Slot blocat cu succes!";
        }
    }

    @Override
    public String deleteSlotBlocat(LocalDateTime slot) {
        SlotBlocat slotBlocatExistent = slotBlocatRepository.findSlotBlocatBySlot(slot);
        if(slotBlocatExistent != null) {
            slotBlocatRepository.delete(slotBlocatExistent);
            return "Slot deblocat cu succes!";
        }else {
            return "Acest slot nu este blocat!";
        }
    }

    @Override
    public List<SlotBlocat> getAllSlotBlocat(LocalDate startWeek) {
        // Calculăm începutul și sfârșitul săptămânii
        LocalDateTime startOfWeek = startWeek.atStartOfDay();
        LocalDateTime endOfWeek = startWeek.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY)).atTime(LocalTime.MAX);

        // Apelăm metoda din repository pentru a obține programările
        return slotBlocatRepository.findSlotBlocatByWeek(startOfWeek, endOfWeek);
    }
}