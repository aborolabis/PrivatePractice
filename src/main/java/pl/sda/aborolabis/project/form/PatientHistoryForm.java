package pl.sda.aborolabis.project.form;

public class PatientHistoryForm {

    private String description;

    public PatientHistoryForm() {
    }

    public PatientHistoryForm(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
