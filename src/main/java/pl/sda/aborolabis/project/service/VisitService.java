package pl.sda.aborolabis.project.service;

import org.springframework.stereotype.Service;
import pl.sda.aborolabis.project.form.PatientHistoryForm;
import pl.sda.aborolabis.project.model.PatientHistory;
import pl.sda.aborolabis.project.model.Visit;

import java.util.List;

@Service
public interface VisitService {

    Visit getVisitById(Long id);

    List<Visit> getAllVisits();

    boolean isVisitCompleted(Visit visit);

    PatientHistory updateVisit(PatientHistoryForm patientHistoryForm);

}
