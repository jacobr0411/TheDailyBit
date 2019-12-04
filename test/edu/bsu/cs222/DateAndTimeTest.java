package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateAndTimeTest {

    @Test
    void getDateAndTime() {
        DateAndTime dateAndTime = new DateAndTime();
        String date = dateAndTime.getDateAndTime();
        System.out.println(date);
    }
}