package com.example.demo.controller;

import com.example.demo.model.Programari;
import com.example.demo.model.SlotBlocat;
import com.example.demo.service.ProgramariService;
import com.example.demo.service.SlotBlocatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/programari")
public class ProgramariController {

    private final ProgramariService programariService;
    private final SlotBlocatService slotBlocatService;

    @Autowired
    public ProgramariController(ProgramariService programariService, SlotBlocatService slotBlocatService){
        this.programariService=programariService;
        this.slotBlocatService = slotBlocatService;
    }

    @PostMapping("/saveProgramare")
    public ResponseEntity<String> programare(@RequestBody Programari programare) {
        String mesaj = programariService.saveProgramare(programare);
        if(mesaj.equals("Programare reușită") ||
                mesaj.equals("Avertizare! Pacientul are deja o programare în această săptămână!")) {
            return new ResponseEntity<>(mesaj, HttpStatus.OK);
        }
        return new ResponseEntity<>(mesaj, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/updateProgramare")
    public ResponseEntity<String> updateProgramare(@RequestBody Programari programareUpdate) {
        String mesaj = programariService.updateProgramare(programareUpdate);
        if (mesaj.equals("Nu există programarea selectată!")) {
            return new ResponseEntity<>(mesaj, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(mesaj, HttpStatus.OK);
        }
    }

    @PostMapping("/deleteProgramare")
    public ResponseEntity<String> deleteProgramare(@RequestBody Programari programareDelete) {
        String mesaj = programariService.deleteProgramare(programareDelete);
        if (mesaj.equals("Nu există programarea selectată!")) {
            return new ResponseEntity<>(mesaj, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(mesaj, HttpStatus.OK);
        }
    }

    @PostMapping("/getProgramariByWeek")
    public ResponseEntity<List<Programari>> getProgramariByWeek(@RequestBody LocalDate startWeek) {
        List<Programari> programari = programariService.getProgramariByWeek(startWeek);
        return new ResponseEntity<>(programari, HttpStatus.OK);
    }

    @PostMapping("/blocareSlot")
    public ResponseEntity<String> blocareSlot(@RequestBody LocalDateTime slot) {
        String mesaj = slotBlocatService.saveSlotBlocat(slot);
        if(mesaj.equals("Slot blocat cu succes!")){
            return new ResponseEntity<>(mesaj, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(mesaj, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deblocareSlot")
    public ResponseEntity<String> deblocareSlot(@RequestBody LocalDateTime slot) {
        String mesaj = slotBlocatService.deleteSlotBlocat(slot);
        if(mesaj.equals("Slot deblocat cu succes!")){
            return new ResponseEntity<>(mesaj, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(mesaj, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getSloturiBlocateByWeek")
    public ResponseEntity<List<SlotBlocat>> blocareSlot(@RequestBody LocalDate startWeek) {
        List<SlotBlocat> slotBlocats = slotBlocatService.getAllSlotBlocat(startWeek);
        return new ResponseEntity<>(slotBlocats, HttpStatus.OK);
    }
}