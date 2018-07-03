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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private VisitRepository visitRepository;

    public void getVisitsInCurrentWeek(){
        Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) details).getUsername();
        Doctor doctor = doctorRepository.findByUsername(username).orElseThrow(() -> new DoctorNotFoundException());

        List<Visit> workingHours = doctor.getWorkingHours();
        LocalDate now = LocalDate.now();
        LocalDate monday = now.minusDays(now.getDayOfWeek().getValue());
        LocalDate saturday = now.plusDays(6 - now.getDayOfWeek().getValue());
    }

    public List<Visit> getTodaysVisits(){
        Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) details).getUsername();
        Doctor doctor = doctorRepository.findByUsername(username).orElseThrow(() -> new DoctorNotFoundException());

        List<Visit> todaysVisits = doctor.getWorkingHours().stream().filter(visit ->
                visit.getVisitTime().getYear() == LocalDateTime.now().getYear()
                        && visit.getVisitTime().getMonth() == LocalDateTime.now().getMonth()
                        && visit.getVisitTime().getDayOfMonth() == LocalDateTime.now().getDayOfMonth())
                .collect(Collectors.toList());

        return todaysVisits;
    }
}
