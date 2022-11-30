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

	public List<SuperUser> getUserProfile(String userId) {
		String query = "select * from superuser where user_id = ?";
		List<Map<String,Object>> resultSet = jdbcTemplate.queryForList(query, new Object[] { userId });
		List<SuperUser> users = new ArrayList<SuperUser>();
        resultSet.forEach(r -> users.add(
			SuperUser.builder()
			.userId(String.valueOf(r.get("user_id")))
			.fname(String.valueOf(r.get("fname")))
			.lname(String.valueOf(r.get("lname")))
			.minit(String.valueOf(r.get("minit")))
			.email(String.valueOf(r.get("email")))
			.build()
		));
		return users;
	}

	public SuperUser getIdFromName(String fname, String lname) {
		String query = "select user_id from superuser service where fname = ? and lname = ?";

        List<Map<String,Object>> rs = jdbcTemplate.queryForList(query, new Object[] { fname, lname });

        SuperUser superUser = SuperUser.builder().userId(String.valueOf(rs.get(0).get("user_id"))).build();

        return superUser;
	}

}
