package com.example.patientservice.controller;

import com.example.patientservice.model.Patient;
import com.example.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        patient.setName(patientDetails.getName());
        patient.setAge(patientDetails.getAge());

        return patientRepository.save(patient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientRepository.deleteById(id);
    }
}
