package com.cs6360.telemedicine.dao;

import com.cs6360.telemedicine.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class AppointmentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Appointment> getAll() {
        String query = "select * from appointment";

        List<Appointment> appointments = new ArrayList<>();
        List<Map<String,Object>> resultSet = jdbcTemplate.queryForList(query);

        resultSet.forEach(r -> appointments.add(
                Appointment.builder()
                        .appointmentId(String.valueOf(r.get("appointment_id")))
                        .appointmentDate((Date)r.get("appointment_date"))
                        .serviceId(String.valueOf(r.get("service_id")))
                        .patientId(String.valueOf(r.get("patient_id")))
                        .doctorId(String.valueOf(r.get("doctor_id")))
                        .build()
        ));

        return appointments;
    }
}