package com.cs6360.telemedicine.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cs6360.telemedicine.model.Credentials;
import com.cs6360.telemedicine.model.UserType;

@Component
public class AuthenticationDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Boolean authenticate(Credentials credentials) {
		String query = "select user_password from superuser where user_id = ?";
		List<Map<String,Object>> resultSet = jdbcTemplate.queryForList(query, new Object[] { credentials.getUsername() });
        String pass = String.valueOf(resultSet.get(0).get("user_password"));
		return credentials.getPassword().equals(pass);
	}

    public Boolean validateUser(Credentials credentials) {
        String query = "";
        if(UserType.PATIENT.toString().equals(credentials.getUserType())) {
            query = "select patient_id from patient where patient_id = ?";
        } else if(UserType.DOCTOR.toString().equals(credentials.getUserType())) {
            query = "select doctor_id from doctor where doctor_id = ?";
        } else if(UserType.PATIENT.toString().equals(credentials.getUserType())) {
            query = "select admin_id from user_admin where admin_id = ?";
        }
        List<Map<String,Object>> resultSet = jdbcTemplate.queryForList(query, new Object[] { credentials.getUsername() });
        return !resultSet.isEmpty();
    }

}
