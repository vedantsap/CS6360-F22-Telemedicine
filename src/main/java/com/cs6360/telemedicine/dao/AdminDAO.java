package com.cs6360.telemedicine.dao;

import com.cs6360.telemedicine.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AdminDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Admin> getAll() {
        String query = "select fname, lname, email, admin_id from user_admin, superuser where user_admin.admin_id = superuser.user_id";

        List<Admin> users = new ArrayList<Admin>();
        List<Map<String,Object>> resultSet = jdbcTemplate.queryForList(query);

        resultSet.forEach(r -> users.add(
                Admin.adminBuilder()
                        .adminId(String.valueOf(r.get("admin_id")))
                        .fname(String.valueOf(r.get("fname")))
                        .lname(String.valueOf(r.get("lname")))
                        .email(String.valueOf(r.get("email")))
                        .build()
        ));

        return users;
    }
}
