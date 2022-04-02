package com.oze.kelechi_oze.services;

import com.oze.kelechi_oze.Utility.CustomCodes;
import com.oze.kelechi_oze.Utility.Utils;
import com.oze.kelechi_oze.exception.NotFoundException;
import com.oze.kelechi_oze.helper.CSVHelper;
import com.oze.kelechi_oze.helper.Validations;
import com.oze.kelechi_oze.io.entities.Patient;
import com.oze.kelechi_oze.io.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
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
}
