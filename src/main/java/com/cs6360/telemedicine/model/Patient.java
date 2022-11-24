package com.cs6360.telemedicine.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends SuperUser {
    private String patientId;
    private Date birthday;
    private String insurance;

    @Builder(builderMethodName = "patientBuilder")
    public Patient(String patientId, String password, String fname, String minit, String lname, String email, Date birthday, String insurance) {
        super(patientId, password, fname, minit, lname, email);
        this.patientId = patientId;
        this.birthday = birthday;
        this.insurance = insurance;
    }
}
