package com.example.patientservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Data
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String specialization;

    @JsonIgnore
    @OneToMany(mappedBy = "doctorId", cascade = CascadeType.ALL)
    private List<Patient> patients;
}
