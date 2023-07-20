package com.mansi.patient.controller;

import com.mansi.patient.entity.Patient;
import org.hibernate.id.IncrementGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.mansi.patient.service.PatientService;

import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/patient") // This means URL's start with /demo (after Application path)
public class PatientController {


    @Autowired
    private PatientService patientService;


    public PatientController(PatientService patientService) {
        super();
        this.patientService = patientService;
    }

    /**
     * Post new patient
     *
     * @param newPatient addedPatient
     * @return patientJsonBody
     */
    @PostMapping(path = "/add") // Map ONLY POST Requests
    @ResponseBody
    public ResponseEntity<Patient> addNewPatient(@RequestBody Patient newPatient) {
        return new ResponseEntity<Patient>(patientService.savePatient(newPatient), HttpStatus.CREATED);
    }


    /**
     *
     * @return ListAllPatients
     */
    @GetMapping(path="/get")
    @ResponseBody
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    //http:localhost:8080/patient/employees/1
    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Integer patientId) {
        return new ResponseEntity<Patient>(patientService.getPatientById(patientId), HttpStatus.OK);

    }

//    @PutMapping("/update/{id}")
//    @ResponseBody
//    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Integer patientId, @RequestBody Patient updatedPatient) {
//
//        return new ResponseEntity<Patient>()
//    }




}