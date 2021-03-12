package com.example.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.User;

/**
 * Interfaz repositorio
 * 
 * @author alberto
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findById(Long id);
}
