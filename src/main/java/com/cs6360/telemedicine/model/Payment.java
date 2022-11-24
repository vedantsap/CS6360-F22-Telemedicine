package com.cs6360.telemedicine.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    private String paymentId;
    private BigDecimal cost;
    private Date paymentDate;
    private String appointmentId;
    private String adminId;
}
