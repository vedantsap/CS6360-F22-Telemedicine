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
public class AppointmentInformation {
    private String appointmentId;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer minute;
    private String timestamp;
    private String serviceName;
    private String patientId;
    private String patientFname;
    private String patientLname;
    private String doctorId;
    private String doctorFname;
    private String doctorLname;
}
