package com.mansi.patient.controller;

import com.mansi.patient.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.mansi.patient.service.PatientService;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/patient") // This means URL's start with /demo (after Application path)
public class PatientController {


    @Autowired
    private PatientService patientService;


    public PatientController(PatientService patientService) {
        super();
        this.patientService = patientService;
    }

    @PostMapping(path = "/add") // Map ONLY POST Requests
    @ResponseBody
    public ResponseEntity<Patient> addNewPatient(@RequestBody Patient newPatient) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

//        Patient p = new Patient();
//        p.setName(newPatient.getName());
//        p.setFamilyName(newPatient.getFamilyName());
//        p.setDateOfBirth(newPatient.getDateOfBirth());
//        p.setSex(newPatient.getSex());
//        p.setHomeAddress(newPatient.getHomeAddress());
//        p.setPhoneNumber(newPatient.getPhoneNumber());
//        patientRepository.save(p);
        return new ResponseEntity<Patient>(patientService.savePatient(newPatient), HttpStatus.CREATED);
    }

//    @GetMapping(path = "/all")
//    public @ResponseBody Iterable<Patient> getAllPatients() {
//        // This returns a JSON or XML with the users
//        return patientService.findAll();
//    }
}