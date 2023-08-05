package com.mansi.patient.repo;

import com.mansi.patient.entity.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoryRepository extends CrudRepository<History, Integer> {

    Optional<History> findByPatientId(Integer patient_id);
}
