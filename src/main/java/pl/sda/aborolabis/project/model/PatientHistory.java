package pl.sda.aborolabis.project.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PatientHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    private LocalDateTime visitDateTime;

    private String description;

    public PatientHistory() {
    }

    public PatientHistory(Patient patient, Doctor doctor, String description) {
        this.patient = patient;
        this.doctor = doctor;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
