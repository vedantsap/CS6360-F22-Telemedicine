package com.cs6360.telemedicine.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cs6360.telemedicine.model.SuperUser;

@Component
public class SuperUserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<SuperUser> getAll() {
		String query = "select fname, lname, user_id from superuser";

		List<SuperUser> users = new ArrayList<SuperUser>();
		List<Map<String,Object>> resultSet = jdbcTemplate.queryForList(query);

		resultSet.forEach(r -> users.add(
			SuperUser.builder()
			.userId(String.valueOf(r.get("user_id")))
			.fname(String.valueOf(r.get("fname")))
			.lname(String.valueOf(r.get("lname")))
			.build()
		));
	
		return users;
	}

}
