package com.cs6360.telemedicine.controller;

import com.cs6360.telemedicine.dao.ServiceDAO;
import com.cs6360.telemedicine.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceDAO serviceDAO;

    @GetMapping("/getAllServices")
    public List<Service> getAllServices() {
        return serviceDAO.getAll();
    }

}