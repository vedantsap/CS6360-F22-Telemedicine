package com.cs6360.telemedicine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs6360.telemedicine.dao.SuperUserDAO;
import com.cs6360.telemedicine.model.Credentials;
import com.cs6360.telemedicine.model.SuperUser;

@RestController
@CrossOrigin
@RequestMapping("/superuser")
public class SuperUserController {
	@Autowired
	private SuperUserDAO superUserDAO;
	@GetMapping("/getAllUsers")
	public List<SuperUser> getAllUsers() {
		return superUserDAO.getAll();
	}

	@PostMapping("/getUserProfile")
	public List<SuperUser> getUserProfile(@RequestBody Credentials user) {
		System.out.println("Retrieving user profile for - " + user.getUsername());
		return superUserDAO.getUserProfile(user.getUsername());
	}

}