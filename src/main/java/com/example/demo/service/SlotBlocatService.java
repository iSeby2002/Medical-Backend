package com.example.demo.service;

import com.example.demo.model.SlotBlocat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public interface SlotBlocatService {
    String saveSlotBlocat(LocalDateTime slot);
    String deleteSlotBlocat(LocalDateTime slot);
    List<SlotBlocat> getAllSlotBlocat(LocalDate startWeek);
}