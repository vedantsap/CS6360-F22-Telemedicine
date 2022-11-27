package com.cs6360.telemedicine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs6360.telemedicine.dao.SuperUserDAO;
import com.cs6360.telemedicine.model.SuperUser;

@RestController
@RequestMapping("/superuser")
public class SuperUserController {
	@Autowired
	private SuperUserDAO superUserDAO;
	@GetMapping("/getAllUsers")
	public List<SuperUser> getAllUsers() {
		return superUserDAO.getAll();
	}

}