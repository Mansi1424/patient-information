package com.mansi.patient.repo;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

import com.mansi.patient.entity.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {
    @Query("SELECT p FROM Patient p WHERE p.familyName = :familyName")
    Optional<Patient> findByFamilyName(@Param("familyName") String familyName);
}