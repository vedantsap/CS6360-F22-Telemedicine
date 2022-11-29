package com.cs6360.telemedicine.controller;

import com.cs6360.telemedicine.dao.AppointmentDAO;
import com.cs6360.telemedicine.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentDAO appointmentDAO;

    @GetMapping("/getAllAppointments")
    public List<Appointment> getAllAppointments() {
        return appointmentDAO.getAll();
    }

    // @PostMapping("/bookAppointment")
    // public Boolean bookAppointment()

}
