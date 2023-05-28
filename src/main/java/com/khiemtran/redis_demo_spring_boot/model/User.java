package com.khiemtran.redis_demo_spring_boot.model;

import com.khiemtran.redis_demo_spring_boot.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@RedisHash("User")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {
	private static final long serialVersionUID = 7156526077883281623L;
	@Id
	@Indexed
	private String id;
	private String firstName;
	private String lastName;
	private int age;
	private String city;

	public UserDto toDto() {
		UserDto userDto = new UserDto();
		userDto.setId(id);
		userDto.setFirstName(firstName);
		userDto.setLastName(lastName);
		userDto.setCity(city);
		userDto.setAge(age);
		return userDto;
	}
}
