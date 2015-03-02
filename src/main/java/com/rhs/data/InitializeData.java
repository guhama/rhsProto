package com.rhs.data;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhs.data.entity.Patient;
import com.rhs.data.repo.PatientRepository;

@Service
public class InitializeData {
	
	@Autowired
	private PatientRepository patRepo;
	
	@PostConstruct
	public void init() {
		System.out.println("START: Patient Iniinializaton...");
		{
		Patient p1 = new Patient();
		p1.setName("Patient 1");
		p1.setAge(22);
		patRepo.save(p1);
		}
		
		{
		Patient p2 = new Patient();
		p2.setName("Patient 2");
		p2.setAge(32);
		patRepo.save(p2);
		}
		
		System.out.println("FINISH: Patient Iniinializaton...");
	}

}
