package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "slot_blocat")
public class SlotBlocat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime slot;

    @Override
    public String toString() {
        return "SlotBlocat{" +
                "slotBlocat=" + slot +
                '}';
    }
}