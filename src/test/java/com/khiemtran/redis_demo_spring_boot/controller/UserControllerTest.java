package com.khiemtran.redis_demo_spring_boot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khiemtran.redis_demo_spring_boot.dto.UserDto;
import com.khiemtran.redis_demo_spring_boot.model.User;
import com.khiemtran.redis_demo_spring_boot.service.UserRedisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
	@InjectMocks
	private UserController userController;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	UserRedisService redisService;
	@Autowired
	private ObjectMapper objectMapper;
	private UserDto userDto;
	private User user;

	@BeforeEach
	public void init() {
		userDto = UserDto.builder().id("1").age(1).city("test").firstName("test").lastName("test").build();
		user = User.builder().id("1").city("test").age(1).lastName("test").firstName("test").build();
	}

	@Test
	public void testCreateUser() throws Exception {
		Mockito.when(redisService.save(user)).thenReturn(user);
		ResultActions response = mockMvc.perform(post("/api/v1/user").contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsString(userDto)));
		response.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGetUser() throws Exception {
		List<User> list = new ArrayList<>(Arrays.asList(user));
		Mockito.when(redisService.getAll()).thenReturn(list);
		ResultActions response = mockMvc.perform(get("/api/v1/user"));
		response.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testDelete() throws Exception {
		String id = "123";
		ResultActions response = mockMvc.perform(delete("/api/v1/user/{id}", 1));
		response.andExpect(MockMvcResultMatchers.status().isOk());
	}
}