package pl.sda.aborolabis.project.Utils;

import pl.sda.aborolabis.project.form.VisitForm;

import java.time.LocalDateTime;

public class TimeUtils {

    public static LocalDateTime getLocalDateTimeFromVisitForm(VisitForm visitForm){
        String[] time = visitForm.getTime().getTime().split(":");
        int hour = Integer.valueOf(time[0]);
        int min = Integer.valueOf(time[1]);
        LocalDateTime formDateTime = LocalDateTime.of
                (visitForm.getDate().getYear(),
                        visitForm.getDate().getMonth().getValue(),
                        visitForm.getDate().getDayOfMonth(),
                        hour, min);

        return formDateTime;
    }
}
