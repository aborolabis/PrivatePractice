package pl.sda.aborolabis.project.model;

import java.util.Arrays;
import java.util.List;

public enum Hours {

    SEVEN_THIRTY ("7:30"),
    EIGHT ("8:00"),
    EIGHT_THIRTY("8:30"),
    NINE("9:00"),
    NINE_THIRTY("9:30"),
    TEN("10:00"),
    TEN_THIRTY("10:30"),
    ELEVEN("11:00"),
    ELEVEN_THIRTY("11:30"),
    TWELVE("12:00"),
    TWELVE_THIRTY("12:30"),
    ONE("13:00"),
    ONE_THIRTY("13:30"),
    TWO("14:00"),
    TWO_THIRTY("14:30"),
    THREE("15:00"),
    THREE_THIRTY("15:30");


    private String time;

    Hours(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public static List<Hours> getAllHours(){
        List<Hours> list = Arrays.asList(Hours.values());
        return list;
    }
}
