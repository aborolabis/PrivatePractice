package pl.sda.aborolabis.project.service;

import org.springframework.stereotype.Service;
import pl.sda.aborolabis.project.exception.DoctorNotFoundException;
import pl.sda.aborolabis.project.form.PatientHistoryForm;
import pl.sda.aborolabis.project.model.PatientHistory;
import pl.sda.aborolabis.project.model.Visit;
import pl.sda.aborolabis.project.repository.PatientHistoryRepository;
import pl.sda.aborolabis.project.repository.VisitRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;
    private final PatientHistoryRepository patientHistoryRepository;

    public VisitServiceImpl(VisitRepository visitRepository, PatientHistoryRepository patientHistoryRepository) {
        this.visitRepository = visitRepository;
        this.patientHistoryRepository = patientHistoryRepository;
    }

    @Override
    public Visit getVisitById(Long id) {
        return visitRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException());
    }

    @Override
    public List<Visit> getAllVisits() {
        return null;
    }

    @Override
    public boolean isVisitCompleted(Visit visit){

        LocalDateTime visitTime = visit.getVisitTime();
        Long doctorId = visit.getDoctor().getId();
        Long patientId = visit.getPatient().getId();

        Optional<PatientHistory> patientHistoryIsDone =
                patientHistoryRepository
                        .findAll()
                        .stream()
                        .filter
                                (patientHistory -> patientHistory
                                        .getVisitDateTime()
                                        .equals(visitTime)
                                        &&
                                        patientHistory
                                                .getDoctor()
                                                .getId()
                                                .equals(doctorId)
                                        &&
                                        patientHistory.getPatient()
                                                .getId()
                                                .equals(patientId))
                        .findFirst();

        if(patientHistoryIsDone.isPresent()){
            return true;
        }
            return false;
    }

    @Override
    public PatientHistory updateVisit(PatientHistoryForm patientHistoryForm) {
        String description = patientHistoryForm.getDescription();
        Long visitID = patientHistoryForm.getVisitID();

        Visit visit = visitRepository.findById(visitID).orElseThrow(DoctorNotFoundException::new);

        PatientHistory patientHistory = new PatientHistory();
        patientHistory.setDoctor(visit.getDoctor());
        patientHistory.setPatient(visit.getPatient());
        patientHistory.setVisitDateTime(visit.getVisitTime());
        patientHistory.setDescription(description);

        return patientHistoryRepository.save(patientHistory);
    }
}
