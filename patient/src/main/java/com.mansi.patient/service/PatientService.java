package com.mansi.patient.service;

import com.mansi.patient.entity.Patient;

import java.util.List;

public interface PatientService {

    Patient savePatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientById(Integer Id);
    Patient getPatientByFamilyName(String familyName);
    Patient updatePatient(Patient patient, Integer id);
    List<Patient> updateMultiplePatients(List<Patient> patients);

}
