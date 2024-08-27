package com.example.demo.repository;

import com.example.demo.model.FisaMedicala;
import com.example.demo.model.Programari;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FisaMedicalaRepository extends CrudRepository<FisaMedicala,Long> {
    List<FisaMedicala> findByProgramari_Pacient_Cnp(long cnp);
    FisaMedicala findFisaMedicalaByProgramari(Programari programari);
}