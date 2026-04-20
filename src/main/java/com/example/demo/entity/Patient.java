package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Type.GenderType;
import com.example.demo.entity.Type.bloodGroupType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@AllArgsConstructor
@Table(name = "patient",
       uniqueConstraints={
           @UniqueConstraint(name="unique_patient_name_birthdate",columnNames = {"name","birthDate"})
       },
       indexes={@Index(name = "idx_patient_birth_date",columnList="birthDate")}
)
@NoArgsConstructor
public class Patient {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderType genderType;

    @Column(name = "email")
    private String email;
  
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private bloodGroupType blood_group;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_insurance_id",referencedColumnName = "Id")
    private Insurance insurance;

    @OneToMany(mappedBy = "patient",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<Appointment> appointmentList = new ArrayList<>();
   

}
