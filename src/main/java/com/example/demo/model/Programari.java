package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "programari")
public class Programari {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "pacient_id")
    private Pacient pacient;
    private LocalDateTime startTime;

    @Override
    public String toString() {
        return "Programari{" +
                "id=" + id +
                ", pacient=" + pacient +
                ", startTime=" + startTime +
                '}';
    }
}