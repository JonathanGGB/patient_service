package com.example.patientservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int age;

    @Column(name = "doctor_id")
    private Long doctorId;
}
