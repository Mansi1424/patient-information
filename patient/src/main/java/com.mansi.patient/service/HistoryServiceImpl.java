package com.mansi.patient.service;

import com.mansi.patient.entity.History;
import com.mansi.patient.exception.ResourceNotFoundException;
import com.mansi.patient.repo.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;
    @Override
    public History saveHistory(History history) {
        return historyRepository.save(history);
    }

    @Override
    public List<History> getAllHistory() {
        return (List<History>) historyRepository.findAll();
    }

    @Override
    public History getHistoryByPatientId(Integer patient_id) {
        Optional<History> history = historyRepository.findByPatientId(patient_id);

        if (history.isPresent()) {
            return history.get();
        } else {
            throw new ResourceNotFoundException("history", "Id", patient_id);
        }
    }


}
