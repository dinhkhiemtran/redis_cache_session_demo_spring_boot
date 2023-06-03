package com.khiemtran.redis_demo_spring_boot.service.imp;

import com.khiemtran.redis_demo_spring_boot.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class UserRedisServiceImpTest {
	@InjectMocks
	UserRedisServiceImp userRedisServiceImp;
	private final HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
	private final RedisTemplate redisTemplate = mock(RedisTemplate.class);
	private final HttpSession httpSession = mock(HttpSession.class);
	private HashOperations hashOperations = mock(HashOperations.class);
	private User user = mock(User.class);

	@BeforeEach
	public void init() {
		user = User.builder().id("1").city("test").firstName("test").lastName("test").age(1).build();
		ReflectionTestUtils.setField(userRedisServiceImp, "httpServletRequest", httpServletRequest);
		ReflectionTestUtils.setField(userRedisServiceImp, "httpServletRequest", httpServletRequest);
		ReflectionTestUtils.setField(userRedisServiceImp, "httpSession", httpSession);
		when(redisTemplate.opsForHash()).thenReturn(hashOperations);
		when(httpSession.getId()).thenReturn("test");
	}

	@Test
	public void saveTest() {
		userRedisServiceImp.save(user);
	}

	@Test
	public void getAllTest() {
		List<User> userList = new ArrayList<>();
		userList.add(user);
		when(redisTemplate.opsForHash().values(anyString())).thenReturn(userList);
		List<User> actual = userRedisServiceImp.getAll();
		Assert.assertEquals(userList, actual);
	}

	@Test
	public void deleteUserRedisTest() {
		String id = "12345";
		userRedisServiceImp.deleteUserRedis(id);
	}
}