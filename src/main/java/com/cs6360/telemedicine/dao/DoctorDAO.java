package com.cs6360.telemedicine.dao;

import com.cs6360.telemedicine.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DoctorDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Doctor> getAll() {
        String query = "select fname, lname, specialty, email, doctor_id from doctor, superuser where doctor.doctor_id = superuser.user_id";

        List<Doctor> users = new ArrayList<Doctor>();
        List<Map<String,Object>> resultSet = jdbcTemplate.queryForList(query);

        resultSet.forEach(r -> users.add(
                Doctor.doctorBuilder()
                        .doctorId(String.valueOf(r.get("doctor_id")))
                        .fname(String.valueOf(r.get("fname")))
                        .lname(String.valueOf(r.get("lname")))
                        .specialty(String.valueOf(r.get("specialty")))
                        .build()
        ));

        return users;
    }
}
