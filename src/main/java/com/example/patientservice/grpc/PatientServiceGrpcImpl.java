package com.example.patientservice.grpc;

import com.example.patientservice.model.Patient;
import com.example.patientservice.repository.PatientRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class PatientServiceGrpcImpl extends PatientServiceGrpc.PatientServiceImplBase {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void getPatientInfo(PatientRequest request, StreamObserver<PatientResponse> responseObserver) {
        Patient patient = patientRepository.findById(request.getId()).orElse(null);
        if (patient != null) {
            PatientResponse response = PatientResponse.newBuilder()
                    .setId(patient.getId())
                    .setName(patient.getName())
                    .setDoctorId(patient.getDoctorId())
                    .build();
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void createPatient(CreatePatientRequest request, StreamObserver<PatientResponse> responseObserver) {
        Patient patient = new Patient();
        patient.setName(request.getName());
        patient.setDoctorId(request.getDoctorId());
        patient = patientRepository.save(patient);
        PatientResponse response = PatientResponse.newBuilder()
                .setId(patient.getId())
                .setName(patient.getName())
                .setDoctorId(patient.getDoctorId())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updatePatient(UpdatePatientRequest request, StreamObserver<PatientResponse> responseObserver) {
        Patient patient = patientRepository.findById((long) request.getId()).orElse(null);
        if (patient != null) {
            patient.setName(request.getName());
            patient.setDoctorId(request.getDoctorId());
            patient = patientRepository.save(patient);
            PatientResponse response = PatientResponse.newBuilder()
                    .setId(patient.getId())
                    .setName(patient.getName())
                    .setDoctorId(patient.getDoctorId())
                    .build();
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void deletePatient(DeletePatientRequest request, StreamObserver<DeletePatientResponse> responseObserver) {
        patientRepository.deleteById((long) request.getId());
        DeletePatientResponse response = DeletePatientResponse.newBuilder()
                .setSuccess(true)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
