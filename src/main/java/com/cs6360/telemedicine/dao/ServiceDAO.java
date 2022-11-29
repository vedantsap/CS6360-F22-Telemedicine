package com.cs6360.telemedicine.dao;

import com.cs6360.telemedicine.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ServiceDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Service getIdFromName(String serviceName) {
        String query = "select service_id from service where service_name = ?";

        List<Map<String,Object>> rs = jdbcTemplate.queryForList(query, new Object[] { serviceName });

        Service service = Service.builder().serviceId(String.valueOf(rs.get(0).get("service_id"))).build();

        return service;
    }
}
