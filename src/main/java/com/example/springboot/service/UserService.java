package com.example.springboot.service;

import java.util.List;

import com.example.springboot.model.User;

/**
 * Interfaz servicio UserService
 * 
 * @author alberto
 *
 */
public interface UserService {

	/**
	 * Retorna todos los elementos de base de datos
	 * 
	 * @return Lista de usuarios
	 */
	public List<User> findAll();

	/**
	 * Retorna un elemento de base de datos que coincide con el id
	 * 
	 * @param id Identificador de la entidad
	 * @return Usuario encontrado null en caso contrario
	 */
	public User findById(Long id);

	/**
	 * Guarda un usuario en base de datos
	 * 
	 * @param user Usuario a guardar
	 * @return Usuario guardado null en caso contrario
	 */
	public User saveUser(User user);

	/**
	 * Actualiza un usuario en base de datos
	 * 
	 * @param user Usuario a modificar
	 * @return Usuario modificado si lo encuentra null en caso contrario
	 */
	public User updateUser(User user);

}