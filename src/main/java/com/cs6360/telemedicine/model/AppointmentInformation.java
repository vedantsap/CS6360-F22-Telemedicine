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
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer minute;
    private String serviceName;
    private String patientId;
    private String doctorFname;
    private String doctorLname;
}
