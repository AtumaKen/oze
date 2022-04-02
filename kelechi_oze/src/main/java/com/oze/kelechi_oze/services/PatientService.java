package com.oze.kelechi_oze.services;

import com.oze.kelechi_oze.Utility.CustomCodes;
import com.oze.kelechi_oze.Utility.Utils;
import com.oze.kelechi_oze.exception.BadRequestException;
import com.oze.kelechi_oze.exception.NotFoundException;
import com.oze.kelechi_oze.helper.CSVHelper;
import com.oze.kelechi_oze.helper.Validations;
import com.oze.kelechi_oze.io.entities.Patient;
import com.oze.kelechi_oze.io.repositories.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.*;

@Service
@Slf4j
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private Validations validations;

    @Autowired
    private CSVHelper csvHelper;



    public Page<Patient> findPatientsOlderThan2(UUID uuid, int page, int pageSize) {
        validations.validateUUID(uuid);
        return patientRepository.findOlderThan2(PageRequest.of(page, pageSize));

    }

    public Map<String, Object> downloadCSV(UUID uuid, Long patientId) {
        validations.validateUUID(uuid);
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new NotFoundException(CustomCodes.NOTFOUND, "Patient with id " + patientId + " not found"));
        Map<String, Object> returnData = new HashMap<>();

         var csvData = csvHelper.patientToCsv(Utils.commaSeperatedPatient(patient));
        returnData.put("csv", csvData);
        returnData.put("patient", patient);
        return returnData;
    }

    @Transactional
    public List<Patient> deleteByDateRange(UUID uuid, String startDate, String endDate) {
        validations.validateUUID(uuid);
        Date start = new Date();
        Date end = new Date();
        if (startDate != null && endDate != null) {
            try {
                 start = Utils.tryParseDate(startDate);
                 end = Utils.tryParseDate(endDate);
                 if(start.after(end))
                     throw new BadRequestException(CustomCodes.BAD_REQUEST, "Start date should be before end date");
                log.info("Dates parsed successfully");
            } catch (ParseException e) {
                throw new BadRequestException(CustomCodes.BAD_REQUEST, "Expected date format is yyyy-MM-dd HH:mm:ss");
            }
        }
        return patientRepository.deleteByLastVisitBetween(start, end);
    }


}
