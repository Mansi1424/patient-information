package com.mansi.patient.service;

import com.mansi.patient.entity.Patient;
import com.mansi.patient.exception.ResourceNotFoundException;
import com.mansi.patient.repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Patient> getAllPatients() {
        return (List<Patient>) patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Integer id) {
        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isPresent()) {
            return patient.get();
        } else {
            throw new ResourceNotFoundException("Patient", "Id", id);
        }

    }

    @Override
    public Patient updatePatient(Patient patient, Integer id) {

        // check whether patient is in DB or not
        Patient existingPatient = patientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "Id", id));

        existingPatient.setName(patient.getName());
        existingPatient.setFamilyName(patient.getFamilyName());
        existingPatient.setDateOfBirth(patient.getDateOfBirth());
        existingPatient.setSex(patient.getSex());
        existingPatient.setHomeAddress(patient.getHomeAddress());
        existingPatient.setPhoneNumber(patient.getPhoneNumber());

        //save existing patient to DB
        patientRepository.save(existingPatient);

        return existingPatient;
    }

    @Override
    public List<Patient> updateMultiplePatients(List<Patient> patients) {

        List<Patient> updatedPatientsList = new ArrayList<>();

        for (Patient updatedPatient : patients) {

            Integer id = updatedPatient.getId();
            // check whether patient is in DB or not
            Patient existingPatient = patientRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Patient", "Id", id));

            existingPatient.setName(updatedPatient.getName());
            existingPatient.setFamilyName(updatedPatient.getFamilyName());
            existingPatient.setDateOfBirth(updatedPatient.getDateOfBirth());
            existingPatient.setSex(updatedPatient.getSex());
            existingPatient.setHomeAddress(updatedPatient.getHomeAddress());
            existingPatient.setPhoneNumber(updatedPatient.getPhoneNumber());

            //save existing patient to DB
            patientRepository.save(updatedPatient);

        }
        return updatedPatientsList;
    }
}
