package com.company.dataService;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class DataToday {

    public LocalDate dateToday() throws ParseException {
        LocalDate myObj = LocalDate.now();

        return myObj;
    }

    public LocalTime timeToday(){
        LocalTime myt = LocalTime.now();

        return myt;
    }
}
