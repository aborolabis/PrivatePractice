package pl.sda.aborolabis.project.service;

import org.springframework.stereotype.Service;
import pl.sda.aborolabis.project.model.Patient;

import java.util.List;

@Service
public interface PatientService {

    List<Patient> findAll();

    List<Patient> findByName(String name);
}
