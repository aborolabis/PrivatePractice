package pl.sda.aborolabis.project.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PatientHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    private LocalDateTime visitDateTime;

    private String description;

    public PatientHistory() {
    }

    public PatientHistory(Patient patient, Doctor doctor, LocalDateTime visitDateTime, String description) {
        this.patient = patient;
        this.doctor = doctor;
        this.visitDateTime = visitDateTime;
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

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getVisitDateTime() {
        return visitDateTime;
    }

    public void setVisitDateTime(LocalDateTime visitDateTime) {
        this.visitDateTime = visitDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
