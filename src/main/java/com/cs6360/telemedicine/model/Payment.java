package com.cs6360.telemedicine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    private String paymentId;
    private Integer cost;
    private String paymentDate;
    private String appointmentId;
    private String adminId;
    private String adminName;
}
