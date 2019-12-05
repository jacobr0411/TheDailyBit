package edu.bsu.cs222;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class DateAndTime {
     String getDateAndTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return dateTime.format(formatter);
    }
}
