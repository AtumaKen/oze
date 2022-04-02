package com.oze.kelechi_oze.helper;

import com.oze.kelechi_oze.exception.NotFoundException;
import com.oze.kelechi_oze.io.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Validations {

    @Autowired
    private StaffRepository staffRepository;

    public void validateUUID(UUID uuid){
        Boolean exists = staffRepository.existsByUuid(uuid);
        if(!exists)
            throw new NotFoundException("404", " Staff with UUID, "+ uuid +" not found");
    }
}
