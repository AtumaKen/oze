package com.oze.kelechi_oze.services;

import com.oze.kelechi_oze.exception.NotFoundException;
import com.oze.kelechi_oze.helper.Validations;
import com.oze.kelechi_oze.io.entities.Staff;
import com.oze.kelechi_oze.io.repositories.StaffRepository;
import com.oze.kelechi_oze.models.request.StaffRequestModel;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class StaffService {

    @Autowired
    private StaffRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private Validations validations;

    public Staff addStaff(StaffRequestModel request){
        Staff staff = new Staff();
        mapper.map(request, staff);
        staff.setUuid(UUID.randomUUID());
        return repository.save(staff);
    }

    public Staff updateStaff(Long id, UUID uuid, StaffRequestModel request){
        validations.validateUUID(uuid);

        Staff updateStaff = repository.findById(id).orElseThrow(() -> new NotFoundException("404", "Staff with requested Id not found"));

        mapper.map(request, updateStaff);
        updateStaff.setUuid(uuid);
        return repository.save(updateStaff);
    }

    public List<Staff> getStaffs() {
        log.info("Total staffs, {}", repository.count());
        return repository.getAll();
    }
}
