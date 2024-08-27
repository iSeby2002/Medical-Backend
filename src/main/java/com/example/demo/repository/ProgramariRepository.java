package com.example.demo.repository;

import com.example.demo.model.Pacient;
import com.example.demo.model.Programari;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProgramariRepository extends CrudRepository<Programari, Long> {
    @Query("SELECT p FROM Programari p WHERE p.startTime >= :startOfDay")
    List<Programari> findAllByDate(@Param("startOfDay") LocalDateTime startOfDay);
    Programari findProgramariByStartTime (LocalDateTime startTime);
    List<Programari> findAllByPacientAndStartTimeBetween(Pacient pacient, LocalDateTime startTimeStart, LocalDateTime startTimeEnd);
    @Query("SELECT p FROM Programari p WHERE p.startTime BETWEEN :startOfWeek AND :endOfWeek")
    List<Programari> findProgramariByWeek(@Param("startOfWeek") LocalDateTime startOfWeek, @Param("endOfWeek") LocalDateTime endOfWeek);
    @Query("SELECT COUNT(p) FROM Programari p WHERE p.startTime BETWEEN :startOfDay AND :endOfDay")
    int countProgramariByDay(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
}