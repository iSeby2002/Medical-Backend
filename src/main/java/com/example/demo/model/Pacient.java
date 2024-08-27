package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "pacient")
public class Pacient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numePrenume;
    private long cnp;

    @Override
    public String toString() {
        return "Pacient{" +
                "id=" + id +
                ", numePrenume='" + numePrenume + '\'' +
                ", cnp=" + cnp +
                '}';
    }
}