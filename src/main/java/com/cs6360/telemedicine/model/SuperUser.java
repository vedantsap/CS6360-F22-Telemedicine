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
public class SuperUser {
    private String userId;
    private String password;
    private String fname;
    private String minit;
    private String lname;
    private String email;
    
}
