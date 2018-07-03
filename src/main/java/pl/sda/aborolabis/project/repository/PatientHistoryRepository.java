package pl.sda.aborolabis.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.aborolabis.project.model.PatientHistory;

@Repository
public interface PatientHistoryRepository extends JpaRepository<PatientHistory, Long> {

}
