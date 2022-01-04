package com.example.demowebmvc.web;

import java.util.List;

import com.example.demowebmvc.dto.user.UserListResponse;
import com.example.demowebmvc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired UserService userService;
	  

	@GetMapping
	public ResponseEntity<?> users() {
		List<UserListResponse> users = userService.users();
		return ResponseEntity.ok().body(users);
	}
}
