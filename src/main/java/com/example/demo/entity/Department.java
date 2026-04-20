package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @CreationTimestamp
    private LocalDateTime created_at;

    @ManyToMany
    @JoinTable(name="dpt_table",
            joinColumns = @JoinColumn(name = "dept_id")
    ,inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private List<Doctor> docterList;

    @OneToOne
    private Doctor headdoctor;
}
