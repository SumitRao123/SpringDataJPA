package com.example.demo.service;

import com.example.demo.entity.Insurance;
import com.example.demo.entity.Patient;
import com.example.demo.repository.InsuranceRepository;
import com.example.demo.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {


    public final InsuranceRepository insuranceRepository;


    public final PatientRepository patientRepository;


    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance,Long patientId){

        Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new RuntimeException("No Id found"));
        patient.setInsurance(insurance);

        insurance.setPatient(patient);
        return patient;
    }
}
