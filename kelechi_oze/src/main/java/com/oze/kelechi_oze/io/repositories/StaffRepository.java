package com.oze.kelechi_oze.io.repositories;

import com.oze.kelechi_oze.io.entities.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {
}
