package com.oze.kelechi_oze.helper;

import com.oze.kelechi_oze.exception.CSVException;
import com.oze.kelechi_oze.io.entities.Patient;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CSVHelper {

    public ByteArrayInputStream patientToCsv(String data){
        String header = "Id, FirstName, LastName, Age, LastVisit";
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL).withHeader(header);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);
            csvPrinter.printRecord(data);
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
           throw new CSVException("97", "Error fetching csv  ");
        }
    }
}
