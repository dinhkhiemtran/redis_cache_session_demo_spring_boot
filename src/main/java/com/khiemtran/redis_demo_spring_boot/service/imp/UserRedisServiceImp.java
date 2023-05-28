package com.khiemtran.redis_demo_spring_boot.service.imp;

import com.khiemtran.redis_demo_spring_boot.model.User;
import com.khiemtran.redis_demo_spring_boot.service.UserRedisService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRedisServiceImp implements UserRedisService {
	@Autowired
	public HttpServletRequest httpServletRequest;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	HttpSession httpSession;

	@Override
	public User save(User user) {
		// SETS menu object in USER-ITEM hashmap at userId key
		redisTemplate.opsForHash().put(httpSession.getId(), user.getId(), user);
		return user;
	}

	@Override
	@Cacheable("user")
	public List<User> getAll() {
		// GET all User values
		return redisTemplate.opsForHash().values(httpSession.getId());
	}

	@Override
	@CacheEvict("user")
	public String deleteUserRedis(String id) {
		redisTemplate.opsForHash().delete(httpSession.getId(), id);
		return "Deleted successfully.";
	}
}
