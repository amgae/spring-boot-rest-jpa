package com.example.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.springboot.model.User;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.UserService;

/**
 * Clase test servicio UserService
 * 
 * @author alberto
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void getUsers() throws Exception {

		List<User> users = new ArrayList<User>();

		User userMock = new User();
		userMock.setId(1L);
		userMock.setName("name");
		userMock.setEmail("mail@mail.com");
		users.add(userMock);

		given(this.userRepository.findAll()).willReturn(users);

		List<User> list = userService.findAll();
		assertEquals(list.size(), 1);
		User user = list.get(0);

		assertEquals(user.getId(), 1L);
		assertEquals(user.getName(), "name");
		assertEquals(user.getEmail(), "mail@mail.com");

	}

	@Test
	public void findById() throws Exception {
		User userMock = new User();
		userMock.setId(1L);
		userMock.setName("name");
		userMock.setEmail("mail@mail.com");

		given(this.userRepository.findById(1L)).willReturn(Optional.of(userMock));

		User user = userService.findById(1L);

		assertEquals(user.getId(), 1L);
		assertEquals(user.getName(), "name");
		assertEquals(user.getEmail(), "mail@mail.com");

		// -----------------------------------------
		given(this.userRepository.findById(1L)).willReturn(Optional.empty());

		user = userService.findById(1L);
		assertEquals(user, null);
	}

	@Test
	public void saveUser() throws Exception {

		User user = new User();
		user.setName("name");
		user.setEmail("mail@mail.com");

		User newUser = new User();
		newUser.setId(1L);
		newUser.setName("name");
		newUser.setEmail("mail@mail.com");

		given(this.userRepository.save(user)).willReturn(newUser);

		User userSaved = userService.saveUser(user);

		assertEquals(userSaved.getId(), 1);
		assertEquals(userSaved.getName(), "name");
		assertEquals(userSaved.getEmail(), "mail@mail.com");
		// -----------------------------------
		userSaved = userService.saveUser(null);

		assertEquals(userSaved, null);

	}

	@Test
	public void updateUser() throws Exception {

		User user = new User();
		user.setId(1L);
		user.setName("name");
		user.setEmail("mail@mail.com");
		given(this.userRepository.findById(1L)).willReturn(Optional.of(user));
		given(this.userRepository.save(user)).willReturn(user);

		User userUpdated = userService.updateUser(user);

		assertEquals(userUpdated.getId(), 1);
		assertEquals(userUpdated.getName(), "name");
		assertEquals(userUpdated.getEmail(), "mail@mail.com");

		// -----------------------------------------
		given(this.userRepository.findById(1L)).willReturn(Optional.empty());

		userUpdated = userService.updateUser(user);

		assertEquals(userUpdated, null);
		// -----------------------------------------
		userUpdated = userService.updateUser(null);

		assertEquals(userUpdated, null);
	}
}
