package com.cs6360.telemedicine.controller;

import com.cs6360.telemedicine.dao.DoctorDAO;
import com.cs6360.telemedicine.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorDAO doctorDAO;

    @GetMapping("/getAllDoctors")
    public List<Doctor> getAllUsers() {
        return doctorDAO.getAll();
    }
}

