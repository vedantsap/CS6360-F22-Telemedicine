package com.cs6360.telemedicine.controller;

import com.cs6360.telemedicine.dao.AppointmentDAO;
import com.cs6360.telemedicine.dao.ServiceDAO;
import com.cs6360.telemedicine.dao.SuperUserDAO;
import com.cs6360.telemedicine.model.AppointmentInformation;
import com.cs6360.telemedicine.model.Service;
import com.cs6360.telemedicine.model.SuperUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentDAO appointmentDAO;
    @Autowired
    private ServiceDAO serviceDAO;
    @Autowired
    private SuperUserDAO superUserDAO;

    @PostMapping("/bookAppointment")
    public Boolean bookAppointment(@RequestBody AppointmentInformation appInfo) {
        Service service = serviceDAO.getIdFromName(appInfo.getServiceName());
        SuperUser doctor = superUserDAO.getIdFromName(appInfo.getDoctorFname(), appInfo.getDoctorLname());

        return appointmentDAO.bookAppointment(appInfo, service.getServiceId(), doctor.getUserId());
    }

}
