package com.omkrushana.coderhack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omkrushana.coderhack.exception.ErrorResponse;
import com.omkrushana.coderhack.model.User;
import com.omkrushana.coderhack.paylods.ApiResponse;
import com.omkrushana.coderhack.paylods.UserDto;
import com.omkrushana.coderhack.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private HttpServletRequest request;

	@GetMapping
	public List<User> getAllUsers() {
		System.out.println("get mapping work");
		return userService.getAllUsers();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable("userId") String userId) {
		System.out.println("get by id -> mapping work");
		User user = userService.getUserById(userId).orElseThrow();
		return ResponseEntity.ok(user);
	}

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		System.out.println("This post is running");
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	@GetMapping("/getBaseUrl")
	public String getBaseUrl() {
		// Get the base URL using HttpServletRequest
		String baseUrl = request.getRequestURL().toString();
		// Remove the endpoint path to get the base URL
		baseUrl = baseUrl.replace(request.getRequestURI(), request.getContextPath());
		return baseUrl;
	}

	@PutMapping("/{userId}")
	public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") String uid) {
		if (userDto.getScore() < 0 || userDto.getScore() > 100) {
            ErrorResponse errorResponse = new ErrorResponse("Score should be between 0 and 100");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} else {
			UserDto updateUser = this.userService.updateUser(uid, userDto.getScore());
			return ResponseEntity.ok(updateUser);

		}

	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") String userId) {
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);

	}

}
