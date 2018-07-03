package pl.sda.aborolabis.project.form;

import pl.sda.aborolabis.project.model.Hours;

import java.time.LocalDate;

public class VisitForm {

    private LocalDate date;

    private Hours time;

    public VisitForm() {
    }

    public VisitForm(LocalDate date, Hours time) {
        this.date = date;
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Hours getTime() {
        return time;
    }

    public void setTime(Hours time) {
        this.time = time;
    }
}
