package com.cs6360.telemedicine.dao;

import com.cs6360.telemedicine.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ServiceDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Service> getAll() {
        String query = "select * from service";

        List<Service> services = new ArrayList<>();
        List<Map<String,Object>> resultSet = jdbcTemplate.queryForList(query);

        resultSet.forEach(r -> services.add(
                Service.builder()
                        .serviceId(String.valueOf(r.get("service_id")))
                        .serviceName(String.valueOf(r.get("service_name")))
                        .price(BigDecimal.valueOf(Double.parseDouble(String.valueOf(r.get("price")))))
                        .build()
        ));

        return services;
    }
}
