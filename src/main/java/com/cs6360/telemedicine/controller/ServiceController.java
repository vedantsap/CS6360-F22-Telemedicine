package com.cs6360.telemedicine.controller;

import com.cs6360.telemedicine.dao.DoctorServiceDAO;
import com.cs6360.telemedicine.model.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private DoctorServiceDAO doctorServiceDAO;

    @GetMapping("/getAllServices")
    public List<DoctorService> getAllServices() {
        return doctorServiceDAO.getAllServices();
    }

}