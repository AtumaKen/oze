package com.oze.kelechi_oze.io.repositories;

import com.oze.kelechi_oze.io.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {
    @Query("Select p from Patient p where p.age > 2")
    Page<Patient> findOlderThan2(Pageable pageable);
}
