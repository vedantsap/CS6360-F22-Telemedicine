package com.cs6360.telemedicine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs6360.telemedicine.dao.AdminDAO;
import com.cs6360.telemedicine.model.Appointment;
import com.cs6360.telemedicine.model.Payment;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private AdminDAO adminDAO;

    @GetMapping("/getConfirmedPayments")
    public List<Payment> getConfirmedPayments() {
        return adminDAO.getConfirmedPayments();
    }

    @GetMapping("/getUnconfirmedAppointments")
    public List<Appointment> getUnconfirmedAppointments() {
        return adminDAO.getUnconfirmedAppointments();
    }

    @PostMapping("/approvePayment")
    public void approvePayment(@RequestBody Payment payment) {
        adminDAO.approvePayment(payment);
    }
}
