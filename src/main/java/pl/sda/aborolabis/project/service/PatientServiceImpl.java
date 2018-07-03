package pl.sda.aborolabis.project.service;

import org.springframework.stereotype.Service;
import pl.sda.aborolabis.project.model.Patient;
import pl.sda.aborolabis.project.repository.PatientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> findByName(String name) {
        return patientRepository.findAll().stream().filter(patient -> patient.getLastName().equals(name) ||
                patient.getFirstName().equals(name)).collect(Collectors.toList());
    }
}
