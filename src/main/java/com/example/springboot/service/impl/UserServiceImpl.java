package com.example.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.model.User;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.UserService;

/**
 * Implementacion servicio UserService
 * 
 * @author alberto
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		LOGGER.info("Invocando findAll ...");
		return userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		LOGGER.info("Invocando findById id={} ...", id);
		if (id != null) {
			Optional<User> user = userRepository.findById(id);
			if (user.isPresent()) {
				LOGGER.info("Usuario encontrado.");
				return user.get();
			} else {
				LOGGER.info("Usuario no encontrado.");
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public User saveUser(User user) {
		LOGGER.info("Invocando saveUser usuario={} ...", user);
		if (user != null) {
			return userRepository.save(user);
		} else {
			return null;
		}
	}

	@Override
	public User updateUser(User user) {
		LOGGER.info("Invocando updateUser usuario={} ...", user);
		if (user != null) {
			Optional<User> findUser = userRepository.findById(user.getId());
			if (findUser.isPresent()) {
				LOGGER.info("Usuario encontrado.");
				User modified = findUser.get();

				modified.setName(user.getName());
				modified.setEmail(user.getEmail());

				LOGGER.info("Guardando usuario.");
				return userRepository.save(modified);
			} else {
				LOGGER.info("Usuario no encontrado.");
				return null;
			}
		} else {
			return null;
		}
	}

}
