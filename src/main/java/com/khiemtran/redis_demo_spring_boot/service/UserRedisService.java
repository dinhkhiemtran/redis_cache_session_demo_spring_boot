package com.khiemtran.redis_demo_spring_boot.service;

import com.khiemtran.redis_demo_spring_boot.model.User;

import java.util.List;

public interface UserRedisService {
	User save(User user);

	List<User> getAll();

	void deleteUserRedis(String id);
}
