package com.oze.kelechi_oze.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oze.kelechi_oze.io.entities.Staff;
import com.oze.kelechi_oze.models.request.StaffRequestModel;
import com.oze.kelechi_oze.services.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.UUID;

import static org.hamcrest.core.Is.is;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StaffController.class)
@RunWith(SpringRunner.class)
@Slf4j
class StaffControllerTest {

    @MockBean
    private StaffService staffService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ModelMapper modelMapper;

    private Staff validStaff;
    private StaffRequestModel request;

    @BeforeEach
    public void setUp() {
        log.info("Before method running");
        validStaff = Staff.builder()
                .id(1L)
                .uuid(UUID.randomUUID())
                .registrationDate(new Date())
                .firstName("John")
                .lastName("Doe")
                .build();

        request = StaffRequestModel.builder()
                .lastName("Doe")
                .firstName("John")
                .build();
    }

    @Test
    void addStaff() throws Exception {
        given(staffService.addStaff(request)).willReturn(validStaff);

        mockMvc.perform(post("/staff")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is("00")))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.firstName", is(validStaff.getFirstName())))
                .andExpect(jsonPath("$.data.lastName", is(validStaff.getLastName())))
                .andExpect(jsonPath("$.data.uuid", is(validStaff.getUuid().toString())));

    }

    @Test
    void updateStaff() {
    }
}