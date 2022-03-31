package com.oze.kelechi_oze.controllers;

import com.oze.kelechi_oze.models.request.StaffRequestModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @PostMapping
    public String addStaff(@RequestBody @Valid StaffRequestModel request){
        return "staff added";
    }

    @PutMapping("/{id}")
    public String updateStaff(@PathVariable Long id, @RequestBody StaffRequestModel request){
        return "updated";
    }
}
