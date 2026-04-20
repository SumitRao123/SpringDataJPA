package com.example.demo.repository;

import com.example.demo.entity.Type.bloodGroupType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  PatientRepository  extends JpaRepository<Patient,Long> {

      @Query("select p from Patient p where p.name  = :name")
      public Patient findPatientWithName(@Param("name") String name);

      @Query("select p from Patient p where p.blood_group = :blood_group")
      public List<Patient> findPatientsWithBloodGroup(@Param("blood_group") bloodGroupType blood_group);

      public Optional<Patient> findPatientByEmail(String email);

      @Query(value = "SELECT gender,count(gender) FROM hospitaldb.patient group by gender",nativeQuery = true)
      public List<Object[]> findPatientWithGender();
}
