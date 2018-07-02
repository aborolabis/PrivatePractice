package pl.sda.aborolabis.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.aborolabis.project.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
