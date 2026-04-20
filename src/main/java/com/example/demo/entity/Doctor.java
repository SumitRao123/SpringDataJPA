package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docter_id;

    private String name;
    private String specialization;

    private String email;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "doctor",cascade =CascadeType.ALL)
    private List<Appointment> appointmentList = new ArrayList<>();

    @ManyToMany(mappedBy = "docterList")
    private Set<Department> departmentSet = new HashSet<>();


}
