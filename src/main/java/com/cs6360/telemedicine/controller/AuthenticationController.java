package com.cs6360.telemedicine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs6360.telemedicine.dao.AuthenticationDAO;
import com.cs6360.telemedicine.model.Credentials;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationDAO authenticationDAO;

	@PostMapping("/authenticate")
	public Boolean authenticate(@RequestBody Credentials credentials) {
		Boolean trueUser = authenticationDAO.validateUser(credentials);
		Boolean validCredentials = authenticationDAO.authenticate(credentials);
		System.out.println("True user = " + true + " valid password = " + validCredentials);
		return trueUser && validCredentials;
	}
}
