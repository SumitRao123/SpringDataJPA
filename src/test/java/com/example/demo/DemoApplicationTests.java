package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Insurance;
import com.example.demo.entity.Type.GenderType;
import com.example.demo.entity.Type.bloodGroupType;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;

@SpringBootTest
class DemoApplicationTests {
  
	@Autowired
	public PatientRepository patientRepo;

	@Autowired
	public InsuranceService insuranceService;

	@Autowired
	public AppointmentService appointmentService;
	 
	@Test
	void contextLoads() {
	}

	@Test
	void TestApp(){
	  List<Patient>	patientList = patientRepo.findAll();
	  Patient p =  patientRepo.findPatientWithName("Aarav Sharma");
	  List<Patient> patientList1 = patientRepo.findPatientsWithBloodGroup(bloodGroupType.A_POSITIVE);
	  Patient p1 = patientRepo.findPatientByEmail("diya.patel@example.com").orElseThrow(()->new RuntimeException("Email not found"));
      List<Object[]> patientList2  = patientRepo.findPatientWithGender();
	  for(Object obj[] : patientList2){
		   System.out.println(obj[0]  + " "  + obj[1]);
	  }
	  System.out.println(patientList);
	  System.out.println(p);
	  System.out.println(patientList1);
	  System.out.println(p1);
	}
	@Test
	public void testInsurance(){
		Insurance insurance = Insurance.builder().provider("HDFC HealthInsurance").policyNumber("12344").
				 validity(LocalDate.of(2030,12,30)).build();
		insuranceService.assignInsuranceToPatient(insurance,1L);
	}

	@Test
	public void testAppointment(){
		Appointment appointment = Appointment.builder().reason("Skin Checkup").
				 localDateTime( LocalDateTime.of(2026, 5, 12, 2, 0) ).
				status("unbooked").build();
		Appointment appointment1 = appointmentService.createAppointment(appointment,1L,1L);

		Appointment appointment2 = appointmentService.reassignAppointment(appointment1.getId(),2L);

	}


}
