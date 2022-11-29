package com.cs6360.telemedicine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cs6360.telemedicine.dao.PatientDAO;
import com.cs6360.telemedicine.model.Credentials;
import com.cs6360.telemedicine.model.Patient;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientDAO patientDAO;

    @PostMapping("/getAccountInfo")
    public Patient getAccountInfo(@RequestBody Credentials credentials) {
        return patientDAO.getAccountInformation(credentials);
    }
}
