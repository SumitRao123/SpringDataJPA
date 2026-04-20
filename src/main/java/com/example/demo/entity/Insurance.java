package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    String policyNumber;
    String provider;
    private LocalDate validity;

    @CreationTimestamp
    private LocalDateTime created_at;

    @OneToOne(mappedBy = "insurance")
    private Patient patient;

}
