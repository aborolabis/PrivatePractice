package pl.sda.aborolabis.project.form;

import pl.sda.aborolabis.project.model.Visit;

public class PatientHistoryForm {

    private String description;

    private Long visitID;

    public PatientHistoryForm() {
    }

    public PatientHistoryForm(String description, Long visitID) {
        this.description = description;
        this.visitID = visitID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVisitID() {
        return visitID;
    }

    public void setVisitID(Long visitID) {
        this.visitID = visitID;
    }
}
