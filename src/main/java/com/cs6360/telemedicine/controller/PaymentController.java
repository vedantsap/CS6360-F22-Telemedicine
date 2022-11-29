package com.cs6360.telemedicine.controller;

import com.cs6360.telemedicine.dao.PaymentDAO;
import com.cs6360.telemedicine.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentDAO paymentDAO;

    @GetMapping("/getAllPayments")
    public List<Payment> getAllPayments() {
        return paymentDAO.getAll();
    }
}