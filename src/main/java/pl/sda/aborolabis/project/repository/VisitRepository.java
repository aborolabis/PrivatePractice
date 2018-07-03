package pl.sda.aborolabis.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.aborolabis.project.model.Visit;

import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, Long> {

}
