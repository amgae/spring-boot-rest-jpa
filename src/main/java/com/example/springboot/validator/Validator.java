package com.example.springboot.validator;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Validator {

	private static final Logger LOGGER = LoggerFactory.getLogger(Validator.class);

	/**
	 * No requerido
	 * @param parameter Parametro a evaluar
	 */
	public static void notRequired(Object parameter) {
		if (parameter != null) {
			LOGGER.error("Parametro no null");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, null);
		}
	}

	/**
	 * Parametro String requerido
	 * @param parameter Parametro a evaluar
	 * @param regex Expresion regular
	 */
	public static void requiredString(String parameter, String regex) {
		if (parameter == null) {
			LOGGER.error("Parametro null");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, null);
		} else {
			if (!Pattern.matches(regex, parameter)) {
				LOGGER.error("Parametro {} no matches {}", new Object[] { parameter, regex });
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, null);
			}
		}
	}

	/**
	 * Parametro Long requerido
	 * @param parameter Parametro a evaluar
	 * @param regex Expresion regular
	 */
	public static void requiredLong(Long parameter, String regex) {
		if (parameter == null) {
			LOGGER.error("Parametro null");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, null);
		} else {
			if (!Pattern.matches(regex, parameter.toString())) {
				LOGGER.error("Parametro {} no matches {}", new Object[] { parameter, regex });
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, null);
			}
		}
	}
	
}
