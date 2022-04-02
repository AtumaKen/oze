package com.oze.kelechi_oze.io.repositories;

import com.oze.kelechi_oze.io.entities.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {
    Boolean existsByUuid(UUID uuid);

    @Query("Select s from Staff s")
    List<Staff> getAll();
}
