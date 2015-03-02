package com.rhs.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.rhs.data.entity.Patient;
import com.rhs.service.PatientService;

@Component
@Scope(value = "request")
public class PatientController {

	private Patient patient = new Patient();

	private List<Patient> patients;

	@Autowired
	private PatientService patSvc;



	@PostConstruct
	public void loadAllPats() {
		patients = patSvc.findAllPats();
		System.out.println(".............@@loadAllPats patients found: "+ patients.size());
	}

	public void savePatient() {
		patSvc.savePatient(patient);
		loadAllPats();
		FacesContext.getCurrentInstance().addMessage(
				"dt",
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Patient saved with success.", null));
	}
	
	public void removePatient(Patient patient) {
		patSvc.removePatient(patient);
		loadAllPats();
		FacesContext.getCurrentInstance().addMessage(
				"dt",
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Patient deleted.", null));
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public PatientService getPatSvc() {
		return patSvc;
	}

	public void setPatSvc(PatientService patSvc) {
		this.patSvc = patSvc;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

}
