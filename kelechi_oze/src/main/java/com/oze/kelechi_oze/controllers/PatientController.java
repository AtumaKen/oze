package com.oze.kelechi_oze.controllers;

import com.oze.kelechi_oze.models.request.PatientRequestModel;
import com.oze.kelechi_oze.models.request.StaffRequestModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @PostMapping
    public String addPatient(@RequestBody @Valid PatientRequestModel request){
        return "Patient added";
    }

    @GetMapping("/{UUID}/olderThan2")
    public String getPatientsOlderThan2(@PathVariable(name = "UUID") UUID uuid){
        return "Patients greater older than 2";
    }

    @GetMapping("/{UUID}/{patientId}/csv")
    public String downloadCSV(@PathVariable(name = "UUID" ) UUID uuid,
                              @PathVariable(name = "patientId") Long patientId){
        return "CSV";
    }

    @DeleteMapping("/UUID")
    public String deleteByDateRange(@PathVariable(name = "UUID") UUID uuid,
                                    @RequestParam(name = "startDate")Date startDate,
                                    @RequestParam(name = "endDate") Date endDate){
        return "Deleted";
    }


}
