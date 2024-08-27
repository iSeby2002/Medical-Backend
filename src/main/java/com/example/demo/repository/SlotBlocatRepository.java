package com.example.demo.repository;

import com.example.demo.model.SlotBlocat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SlotBlocatRepository extends CrudRepository<SlotBlocat,Long> {
    SlotBlocat findSlotBlocatBySlot(LocalDateTime slot);
    @Query("SELECT s FROM SlotBlocat s WHERE s.slot BETWEEN :startOfWeek AND :endOfWeek")
    List<SlotBlocat> findSlotBlocatByWeek(@Param("startOfWeek") LocalDateTime startOfWeek, @Param("endOfWeek") LocalDateTime endOfWeek);
}