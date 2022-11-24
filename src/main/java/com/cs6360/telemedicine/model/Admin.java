package com.cs6360.telemedicine.model;

import lombok.*;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class Admin extends SuperUser {
    private String adminId;
    @Builder(builderMethodName = "adminBuilder")
    public Admin(String adminId, String password, String fname, String minit, String lname, String email) {
        super(adminId, password, fname, minit, lname, email);
        this.adminId = adminId;
    }
}
