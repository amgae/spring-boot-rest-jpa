package com.example.springboot;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.User;
import com.example.springboot.service.UserService;
import com.example.springboot.validator.Validator;

/**
 * Calse controlador REST con llamadas a metodos GET,POST,PUT
 * 
 * @author alberto
 *
 */
@RestController
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String index() throws MethodArgumentNotValidException {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	public List<User> getUsers() {
		LOGGER.info("Invocando metodo obtener usuarios ...");
		return userService.findAll();
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = "application/json")
	public User getUser(@PathVariable Long id) {
		LOGGER.info("Invocando metodo obtener usuario por id ...");
		return userService.findById(id);
	}

	@RequestMapping(value = "/users/save", method = RequestMethod.POST, produces = "application/json")
	public User saveUser(@RequestBody User user) {
		LOGGER.info("Invocando metodo salvar usuario ...");
		Validator.notRequired(user.getId());
		Validator.requiredString(user.getName(), "(.){1,50}");
		Validator.requiredString(user.getEmail(), "(.){1,50}");
		return userService.saveUser(user);
	}

	@RequestMapping(value = "/users/update", method = RequestMethod.PUT, produces = "application/json")
	public User updateUser(@RequestBody User user) {
		LOGGER.info("Invocando metodo actualizar usuario ...");
		Validator.requiredLong(user.getId(),"[0-9]{1,9}");
		Validator.requiredString(user.getName(), "(.){1,50}");
		Validator.requiredString(user.getEmail(), "(.){1,50}");
		return userService.updateUser(user);
	}
}