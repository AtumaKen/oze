package com.oze.kelechi_oze.services;

import com.oze.kelechi_oze.exception.NotFoundException;
import com.oze.kelechi_oze.io.entities.Staff;
import com.oze.kelechi_oze.io.repositories.StaffRepository;
import com.oze.kelechi_oze.models.request.PatientRequestModel;
import com.oze.kelechi_oze.models.request.StaffRequestModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class StaffService {

    @Autowired
    private StaffRepository repository;

    @Autowired
    private ModelMapper mapper;

    public Staff addStaff(StaffRequestModel request){
        Staff staff = new Staff();
        mapper.map(request, staff);
        staff.setUuid(UUID.randomUUID());
        return repository.save(staff);
    }

    public Staff updateStaff(Long id, UUID uuid, StaffRequestModel request){
        Boolean exists = repository.existsByUuid(uuid);
        if(!exists)
            throw new NotFoundException("404", " Staff with UUID, "+ uuid +" not found");

        Staff updateStaff = repository.findById(id).orElseThrow(() -> new NotFoundException("404", "Staff with requested Id not found"));

        mapper.map(request, updateStaff);
        updateStaff.setUuid(uuid);
        return repository.save(updateStaff);
    }

}
