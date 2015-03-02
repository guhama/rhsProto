package com.rhs.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rhs.data.entity.Patient;

public interface PatientRepository extends JpaRepository <Patient, Integer> {

	
}
