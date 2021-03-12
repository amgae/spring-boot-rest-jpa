package com.example.springboot;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.springboot.model.User;
import com.example.springboot.service.UserService;

/**
 * Clase test REST UserController
 * 
 * @author alberto
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService userService;

	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("Greetings from Spring Boot!")));
	}

	@Test
	public void getUsers() throws Exception {

		List<User> users = new ArrayList<User>();

		User user = new User();
		user.setId(1L);
		user.setName("name");
		user.setEmail("mail@mail.com");

		users.add(user);

		given(this.userService.findAll()).willReturn(users);

		mvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath(".name").value("name"))
				.andExpect(MockMvcResultMatchers.jsonPath(".email").value("mail@mail.com"));

	}

	@Test
	public void getUser() throws Exception {
		User user = new User();
		user.setId(1L);
		user.setName("name");
		user.setEmail("mail@mail.com");

		given(this.userService.findById(1L)).willReturn(user);

		mvc.perform(MockMvcRequestBuilders.get("/users/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath(".name").value("name"))
				.andExpect(MockMvcResultMatchers.jsonPath(".email").value("mail@mail.com"));

		// -------------------------------------------------------
		given(this.userService.findById(1L)).willReturn(null);

		mvc.perform(MockMvcRequestBuilders.get("/users/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(equalTo("")));

	}

	@Test
	public void saveUser() throws Exception {
		User user = new User();
		user.setName("nuevo");
		user.setEmail("mail@mail.com");

		User newUser = new User();
		newUser.setId(1L);
		newUser.setName("nuevo");
		newUser.setEmail("mail@mail.com");
		given(this.userService.saveUser(user)).willReturn(newUser);

		mvc.perform(MockMvcRequestBuilders.post("/users/save").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"nuevo\",\"email\":\"mail@mail.com\"}")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath(".name").value("nuevo"))
				.andExpect(MockMvcResultMatchers.jsonPath(".email").value("mail@mail.com"));
	}

	@Test
	public void updateUser() throws Exception {

		User user = new User();
		user.setId(1L);
		user.setName("modificado");
		user.setEmail("modificado@mail.com");

		given(this.userService.updateUser(user)).willReturn(user);

		mvc.perform(MockMvcRequestBuilders.put("/users/update").accept(MediaType.APPLICATION_JSON)
				.content("{\"id\":1,\"name\":\"modificado\",\"email\":\"modificado@mail.com\"}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath(".name").value("modificado"))
				.andExpect(MockMvcResultMatchers.jsonPath(".email").value("modificado@mail.com"));

		// ----------------------------------------------
		given(this.userService.updateUser(user)).willReturn(null);

		mvc.perform(MockMvcRequestBuilders.put("/users/update").accept(MediaType.APPLICATION_JSON)
				.content("{\"id\":1,\"name\":\"modificado\",\"email\":\"modificado@mail.com\"}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("")));

	}
}
