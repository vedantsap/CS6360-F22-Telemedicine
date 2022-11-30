package com.cs6360.telemedicine.model;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends SuperUser {
    private String doctorId;
    private String specialty;

    @Builder(builderMethodName = "doctorBuilder")
    public Doctor(String doctorId, String password, String fname, String minit, String lname, String email, String specialty) {
        super(doctorId, password, fname, minit, lname, email);
        this.doctorId = doctorId;
        this.specialty = specialty;
    }
}
