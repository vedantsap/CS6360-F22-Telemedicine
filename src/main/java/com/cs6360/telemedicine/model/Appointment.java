package com.cs6360.telemedicine.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {
    private String appointmentId;
    private Date appointmentDate;
    private String serviceId;
    private String patientId;
    private String doctorId;
}
