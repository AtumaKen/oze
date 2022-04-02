package com.oze.kelechi_oze.services;

import com.oze.kelechi_oze.helper.Validations;
import com.oze.kelechi_oze.io.entities.Patient;
import com.oze.kelechi_oze.io.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private Validations validations;


    public Page<Patient> findPatientsOlderThan2(UUID uuid, int page, int pageSize) {
        validations.validateUUID(uuid);
        return patientRepository.findOlderThan2(PageRequest.of(page, pageSize));

    }
}
