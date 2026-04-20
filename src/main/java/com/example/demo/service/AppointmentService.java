package com.example.demo.service;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AppointmentService {

    public final AppointmentRepository appointmentRepository;
    public final DoctorRepository doctorRepository;
    public final PatientRepository patientRepository;



    @Transactional
    public Appointment createAppointment(Appointment appointment,Long patient_id,Long docter_id){
        Patient patient = patientRepository.findById(patient_id).orElseThrow(()-> new RuntimeException("Id not found"));
        patient.getAppointmentList().add(appointment);
        Doctor doctor = doctorRepository.findById(docter_id).orElseThrow(()->new RuntimeException("Id not found"));
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        doctor.getAppointmentList().add(appointment);
        appointment.setStatus("Booked");
        appointmentRepository.save(appointment);
        return  appointment;
    }

    @Transactional
    public Appointment reassignAppointment(Long AppointmentId,  Long DoctorId){
        Appointment appointment = appointmentRepository.findById(AppointmentId).orElseThrow(()-> new RuntimeException("Id not found"));
        Doctor doctor = doctorRepository.findById(DoctorId).orElseThrow(()->new RuntimeException("Not found"));
        appointment.setDoctor(doctor);
        doctor.getAppointmentList().add(appointment);
        return appointment;


    }
}
