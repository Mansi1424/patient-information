package com.mansi.patient.service;

import com.mansi.patient.entity.History;
import com.mansi.patient.entity.Patient;

import java.util.List;

public interface HistoryService {
    History saveHistory(History history);

    List<History> getAllHistory();

    History getHistoryByPatientId(Integer patient_id);

}
