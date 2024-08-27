package com.example.demo.repository;

import com.example.demo.model.Pacient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacientRepository extends CrudRepository<Pacient,Long> {
    Pacient findPacientByCnp(Long cnp);
}