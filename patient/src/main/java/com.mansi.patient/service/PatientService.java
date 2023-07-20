package com.mansi.patient.service;

import com.mansi.patient.entity.Patient;

import java.util.List;

public interface PatientService {

    Patient savePatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientById(Integer Id);
    Patient updatePatient(Patient patient, Integer id);
}
