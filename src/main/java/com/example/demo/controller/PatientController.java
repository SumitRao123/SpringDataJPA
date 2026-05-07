package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;

@RestController
@RequestMapping("/api")
public class PatientController {
   
    @Autowired
    public PatientRepository patientRepo;

    @GetMapping("/greet")
    public String getValue(){
        return "Hello";
    }
     
    @GetMapping("/")
     public List<Patient> findAll(){
            return patientRepo.findAll();
     }
}
