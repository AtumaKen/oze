package com.oze.kelechi_oze.controllers;

import com.oze.kelechi_oze.io.entities.Patient;
import com.oze.kelechi_oze.models.request.PatientRequestModel;
import com.oze.kelechi_oze.models.request.StaffRequestModel;
import com.oze.kelechi_oze.models.response.Response;
import com.oze.kelechi_oze.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService service;

    @PostMapping
    public String addPatient(@RequestBody @Valid PatientRequestModel request){
        return "Patient added";
    }

    @GetMapping("/{UUID}/olderThan2")
    public ResponseEntity<Response> getPatientsOlderThan2(@PathVariable(name = "UUID") UUID uuid,
                                                          @RequestParam(name = "page") Integer page,
                                                          @RequestParam(name = "pageSize") Integer pageSize){
        Response response = new Response();
        Page<Patient> patientsOlderThan2 = service.findPatientsOlderThan2(uuid, page, pageSize);
        response.setCode("00");
        response.setMessage("Data retrived successfully");
        response.setData(patientsOlderThan2);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
