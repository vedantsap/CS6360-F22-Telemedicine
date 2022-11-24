package com.cs6360.telemedicine.dao;

import com.cs6360.telemedicine.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class PaymentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Payment> getAll() {
        String query = "select * from payment";

        List<Payment> payments = new ArrayList<>();
        List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(query);

        resultSet.forEach(r -> payments.add(
                Payment.builder()
                        .paymentId(String.valueOf(r.get("payment_id")))
                        .cost(BigDecimal.valueOf(Double.parseDouble(String.valueOf(r.get("cost")))))
                        .paymentDate((Date)r.get("payment_date"))
                        .appointmentId(String.valueOf(r.get("appointment_id")))
                        .adminId(String.valueOf(r.get("admin_id")))
                        .build()
        ));

        return payments;
    }
}
