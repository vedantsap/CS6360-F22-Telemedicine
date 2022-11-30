package com.cs6360.telemedicine.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Service {
    private String serviceId;
    private String serviceName;
    private BigDecimal price;
}
