package com.khiemtran.redis_demo_spring_boot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khiemtran.redis_demo_spring_boot.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	@JsonProperty("id")
	private String id;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("age")
	private int age;
	@JsonProperty("city")
	private String city;

	public User toEntity() {
		User user = new User();
		user.setId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setCity(city);
		user.setAge(age);
		return user;
	}
}
