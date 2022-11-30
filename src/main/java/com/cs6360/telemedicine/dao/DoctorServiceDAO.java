package com.cs6360.telemedicine.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.cs6360.telemedicine.model.DoctorService;


@Component
public class DoctorServiceDAO {
    @Autowired
	private JdbcTemplate jdbcTemplate;

	public List<DoctorService> getAllServices() {
		String query = 
			"select service_name, price, fname, minit, lname, doctor.specialty, email " + 
			"from (superuser join doctor on user_id = doctor_id), (service natural join doctor_services)" + 
			"where doctor.specialty = doctor_services.specialty;";

		List<DoctorService> doctorServices = new ArrayList<>();
		List<Map<String,Object>> resultSet = jdbcTemplate.queryForList(query);

		resultSet.forEach(r -> 
			doctorServices.add(DoctorService.builder()
					.serviceName(String.valueOf(r.get("service_name")))
					.price(String.valueOf(r.get("price")))
					.fname(String.valueOf(r.get("fname")))
					.lname(String.valueOf(r.get("lname")))
					.email(String.valueOf(r.get("email")))
					.specialty(String.valueOf(r.get("specialty")))
					.build())
		);
	
		return doctorServices;
	}

}
