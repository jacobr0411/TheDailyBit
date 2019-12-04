package edu.bsu.cs222;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime {
     String getDateAndTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy HH:mm");
        return dateTime.format(formatter);
    }
}
