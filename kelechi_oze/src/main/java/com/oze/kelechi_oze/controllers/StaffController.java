package com.oze.kelechi_oze.controllers;

import com.oze.kelechi_oze.io.entities.Staff;
import com.oze.kelechi_oze.models.request.StaffRequestModel;
import com.oze.kelechi_oze.models.response.Response;
import com.oze.kelechi_oze.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping
    public ResponseEntity<Response> addStaff(@RequestBody @Valid StaffRequestModel request){
        Response response = new Response();
        Staff staff = staffService.addStaff(request);
        response.setCode("00");
        response.setMessage("Patient saved successfully");
        response.setData(staff);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateStaff(@PathVariable Long id,
                                                @RequestParam(name = "uuid") UUID uuid,
                                                @RequestBody @Valid StaffRequestModel request){
        Response response = new Response();
        Staff staff = staffService.updateStaff(id, uuid,request);
        response.setCode("00");
        response.setMessage("Patient saved successfully");
        response.setData(staff);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
