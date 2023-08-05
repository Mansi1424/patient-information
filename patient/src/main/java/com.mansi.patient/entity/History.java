package com.mansi.patient.entity;

import jakarta.persistence.*;

@Entity
public class History {

    public History() {
        super();
    }

    public History(Integer patient_id, String history) {
        this.patientId = patient_id;
        this.history = history;
    }

    public History(Integer id, Integer patientId) {
        this.id = id;
        this.patientId = patientId;
    }

    public History(Integer id, Integer patient_id, String history) {
        this.id = id;
        this.patientId = patient_id;
        this.history = history;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer patientId;

    private String history;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
