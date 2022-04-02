package com.oze.kelechi_oze.Utility;

import com.oze.kelechi_oze.io.entities.Patient;
import org.springframework.stereotype.Component;


public class Utils {

    public static String commaSeperatedPatient(Patient patient){
        return patient.getId() + "," + patient.getFirstName()
                + "," + patient.getLastName() + "," +patient.getAge()
                + "," + patient.getLastVisit();
    }
}
