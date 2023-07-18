package com.mansi.patient.service;

import com.mansi.patient.entity.Patient;
import com.mansi.patient.repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        super();
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }
}
