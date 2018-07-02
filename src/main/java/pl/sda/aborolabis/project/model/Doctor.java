package pl.sda.aborolabis.project.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Doctor extends User {

    private String title;

    private String specialization;

    @OneToMany(mappedBy = "doctor")
    private List<Visit> workingHours;

    public Doctor() {

    }

    public Doctor(String username, String password, String firstName, String lastName, Person person, String title,
                  String specialization, List<Visit> workingHours) {
        super(username, password, firstName, lastName, person);
        this.title = title;
        this.specialization = specialization;
        this.workingHours = workingHours;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Visit> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<Visit> workingHours) {
        this.workingHours = workingHours;
    }

    public void setVisit(Visit visit) {
        if(workingHours.isEmpty()){
            workingHours = new ArrayList<>();
        } else {
            workingHours.add(visit);
        }
    }

    public void deleteVisit(Visit visit){
        workingHours.remove(visit);
    }
}
