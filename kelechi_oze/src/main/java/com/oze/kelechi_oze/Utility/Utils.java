package com.oze.kelechi_oze.Utility;

import com.oze.kelechi_oze.io.entities.Patient;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utils {

    public static String commaSeperatedPatient(Patient patient){
        return patient.getId() + "," + patient.getFirstName()
                + "," + patient.getLastName() + "," +patient.getAge()
                + "," + patient.getLastVisit();
    }


    public static Date tryParseDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
    }
}
