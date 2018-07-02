package pl.sda.aborolabis.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.sda.aborolabis.project.exception.DoctorNotFoundException;
import pl.sda.aborolabis.project.model.Doctor;
import pl.sda.aborolabis.project.model.Visit;
import pl.sda.aborolabis.project.repository.DoctorRepository;
import pl.sda.aborolabis.project.repository.VisitRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl  implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private VisitRepository visitRepository;

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findById(String id) {
        return doctorRepository.findById(Long.valueOf(id)).orElseThrow(() -> new DoctorNotFoundException());
    }

    @Override
    public List<Doctor> findByName(String name) {
        return doctorRepository.findAll().stream().filter(doc -> doc.getLastName().equals(name) ||
                doc.getFirstName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Visit> getAllVisits() {
        Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) details).getUsername();
        Doctor doctor = doctorRepository.findByUsername(username).orElseThrow(() -> new DoctorNotFoundException());
        List<Visit> listOfVisits = doctorRepository.findAll().stream().filter(doc -> doc.getId() == doctor.getId()).flatMap(doc -> doc.getWorkingHours().stream()).collect(Collectors.toList());
        return listOfVisits;
    }

    @Override
    public Doctor addVisit(Visit visit) {
        Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) details).getUsername();
        Doctor doctor = doctorRepository.findByUsername(username).orElseThrow(() -> new DoctorNotFoundException());
        visit.setDoctor(doctor);
        Visit savedVisit = visitRepository.save(visit);
        doctor.setVisit(savedVisit);
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor deleteVisit(Visit visit) {
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        String username = ((UserDetails) details).getUsername();
        Doctor doctor = doctorRepository.findByUsername(username).orElseThrow(() -> new DoctorNotFoundException());
        Visit deletedVisit = doctor.getWorkingHours().stream().filter(v -> v.getDoctor().getId() == (visit.getDoctor().getId())).findFirst().orElseThrow(() -> new DoctorNotFoundException());
        doctor.deleteVisit(deletedVisit);
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(Doctor doctor) {
        doctorRepository.delete(doctor);
    }
}
