package com.rhs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhs.data.entity.Patient;
import com.rhs.data.repo.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patRepo;
	
	

	public List<Patient> findAllPats() {
		return patRepo.findAll();
	}
	
	public Patient savePatient(Patient pat) {
		return patRepo.save(pat);
	}
	
	public void removePatient(Patient pat) {
		patRepo.delete(pat);
	}
}
