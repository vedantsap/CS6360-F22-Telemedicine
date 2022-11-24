package com.cs6360.telemedicine.controller;

import com.cs6360.telemedicine.dao.AdminDAO;
import com.cs6360.telemedicine.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminDAO adminDAO;

    @GetMapping("/getAllAdmins")
    public List<Admin> getAllUsers() {
        return adminDAO.getAll();
    }
}
