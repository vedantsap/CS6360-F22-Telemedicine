package com.cs6360.telemedicine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cs6360.telemedicine.dao.PatientDAO;
import com.cs6360.telemedicine.model.Patient;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientDAO patientDAO;

    @GetMapping("/getAllPatients")
    public List<Patient> getAllUsers() {
        return patientDAO.getAll();
    }
}
