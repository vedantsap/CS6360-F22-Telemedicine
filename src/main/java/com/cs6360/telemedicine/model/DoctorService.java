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
public class DoctorService {
    private String serviceName;
    private String price;
    private String fname;
    private String lname;
    private String specialty;
    private String email;
}
