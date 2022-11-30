package com.cs6360.telemedicine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs6360.telemedicine.dao.AppointmentDAO;
import com.cs6360.telemedicine.model.AppointmentInformation;
import com.cs6360.telemedicine.model.Doctor;

@RestController
@CrossOrigin
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private AppointmentDAO appointmentDAO;

    @PostMapping("/getAllUpcomingAppointments")
    public List<AppointmentInformation> getAllUpcomingAppointments(@RequestBody Doctor doctor) {
        return appointmentDAO.getAllUpcomingAppointments(doctor.getDoctorId());
    }

    @PostMapping("/getAllPastAppointments")
    public List<AppointmentInformation> getAllPastAppointments(@RequestBody Doctor doctor) {
        return appointmentDAO.getAllPastAppointments(doctor.getDoctorId());
    }

}