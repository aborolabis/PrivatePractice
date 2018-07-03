package pl.sda.aborolabis.project.service;

import org.springframework.stereotype.Service;
import pl.sda.aborolabis.project.form.VisitForm;
import pl.sda.aborolabis.project.model.Doctor;
import pl.sda.aborolabis.project.model.Visit;

import javax.print.Doc;
import java.util.List;

@Service
public interface DoctorService {

    List<Doctor> findAll();

    Doctor findById(String id);

    List<Doctor> findByName(String name);

    Doctor createDoctor(Doctor doctor);

    Doctor findCurrentDoctor();

    List<Visit> getAllVisits();

    Doctor addVisit(VisitForm visitForm);

    Doctor deleteVisit(Visit visit);

    void deleteDoctor(Doctor doctor);
}
