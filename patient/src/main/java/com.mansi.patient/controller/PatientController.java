package com.mansi.patient.controller;

import com.mansi.patient.entity.Patient;
import com.mansi.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
     * POST NEW Patient with request Params
     * @param family familyName
     * @param given name
     * @param dob dateofbirth
     * @param sex gender
     * @param address home address
     * @param phone phone Number
     * @return added patient successful
     */
    @PostMapping(path = "/add") // Map ONLY POST Requests
    @ResponseBody
    public ResponseEntity<Patient> addNewPatient(@RequestParam String family, @RequestParam String given, @RequestParam String dob, @RequestParam String sex, @RequestParam String address, @RequestParam String phone) {
        Patient newPatient = new Patient();
        newPatient.setFamilyName(family);
        newPatient.setName(given);
        newPatient.setDateOfBirth(dob);
        newPatient.setSex(sex);
        newPatient.setHomeAddress(address);
        newPatient.setPhoneNumber(phone);

        return new ResponseEntity<Patient>(patientService.savePatient(newPatient), HttpStatus.CREATED);
    }

    /**
     * POST:  New Patient With Patient Object as input
     * @param newPatient
     * @return
     */
    @PostMapping(path = "/addNew") // Map ONLY POST Requests
    @ResponseBody
    public ResponseEntity<Patient> addNewPatientRequestParams(@RequestBody Patient newPatient) {
        return new ResponseEntity<Patient>(patientService.savePatient(newPatient), HttpStatus.CREATED);
    }



    /**
     * GET all patients
     *
     * @return ListAllPatients
     */
    @GetMapping(path="/get")
    @ResponseBody
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }


    /**
     * GET patient by ID
     *
     * @param patientId Id of patient
     * @return One Patient
     */
    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Integer patientId) {
        return new ResponseEntity<Patient>(patientService.getPatientById(patientId), HttpStatus.OK);

    }

    @GetMapping("/getByFamily/{familyName}")
    @ResponseBody
    public ResponseEntity<Patient> getPatientByFamily(@PathVariable("familyName") String familyName) {
        return new ResponseEntity<Patient>(patientService.getPatientByFamilyName(familyName), HttpStatus.OK);

    }

    /**
     * UPDATE patient by Id
     * @param patientId Id of patient
     * @param updatedPatient Object of updated patient
     * @return Patient updated
     */
    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Integer patientId, @RequestBody Patient updatedPatient) {

        return new ResponseEntity<Patient>(patientService.updatePatient(updatedPatient, patientId), HttpStatus.OK);
    }

    @PutMapping("/update")
    @ResponseBody
    public List<Patient> updateMultiplePatients(@RequestBody List<Patient> updatedPatients) {
        // Implement the logic to update the users in your service
        // For example, you might iterate through the updatedUsers list and update the database.

        // Return a response indicating success or failure
        return patientService.updateMultiplePatients((updatedPatients));

    }




}