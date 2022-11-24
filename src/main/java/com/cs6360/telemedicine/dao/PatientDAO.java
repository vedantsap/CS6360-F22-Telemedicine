package com.cs6360.telemedicine.dao;

import com.cs6360.telemedicine.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class PatientDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Patient> getAll() {
        String query = "select fname, lname, birthday, insurance, patient_id from patient, superuser where patient.patient_id = superuser.user_id";

        List<Patient> users = new ArrayList<Patient>();
        List<Map<String,Object>> resultSet = jdbcTemplate.queryForList(query);

        resultSet.forEach(r -> users.add(
                Patient.patientBuilder()
                        .patientId(String.valueOf(r.get("patient_id")))
                        .fname(String.valueOf(r.get("fname")))
                        .lname(String.valueOf(r.get("lname")))
                        .birthday((Date)(r.get("birthday")))
                        .insurance(String.valueOf(r.get("insurance")))
                        .build()
        ));

        return users;
    }
}
