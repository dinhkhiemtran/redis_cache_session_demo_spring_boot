package com.khiemtran.redis_demo_spring_boot.controller;

import com.khiemtran.redis_demo_spring_boot.dto.UserDto;
import com.khiemtran.redis_demo_spring_boot.model.User;
import com.khiemtran.redis_demo_spring_boot.service.UserRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	UserRedisService redisService;

	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
		User userRequest = userDto.toEntity();
		User user = redisService.save(userRequest);
		UserDto userResponse = user.toDto();
		return ResponseEntity.ok().body(userResponse);
	}

	@GetMapping("/user")
	public ResponseEntity<?> getAllUsers() {
		List<User> users = redisService.getAll();
		List<UserDto> userResponse = users.stream().map(user -> user.toDto()).collect(Collectors.toList());
		return ResponseEntity.ok().body(userResponse);
	}

	@DeleteMapping("user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(name = "id") String id) {
		redisService.deleteUserRedis(id);
		return ResponseEntity.ok().body("Successfully.");
	}
}
