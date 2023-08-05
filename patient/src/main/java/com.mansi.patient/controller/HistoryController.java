package com.mansi.patient.controller;

import com.mansi.patient.entity.History;
import com.mansi.patient.entity.Patient;
import com.mansi.patient.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }


    @PostMapping(path = "/add") // Map ONLY POST Requests
    @ResponseBody
    public ResponseEntity<History> addNewHistory(@RequestBody History newHistory) {
        return new ResponseEntity<History>(historyService.saveHistory(newHistory), HttpStatus.CREATED);
    }

    @GetMapping(path = "/get") // Map ONLY POST Requests
    @ResponseBody
    public List<History> getAllHistory() {
        return historyService.getAllHistory();
    }

    @GetMapping(path = "/get/{id}")
    @ResponseBody
    public ResponseEntity<History> getHistoryById(@PathVariable("id") Integer patient_id) {
        return new ResponseEntity<History>(historyService.getHistoryByPatientId(patient_id), HttpStatus.OK);
    }


}
