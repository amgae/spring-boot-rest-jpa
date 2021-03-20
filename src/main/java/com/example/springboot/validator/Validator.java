package com.example.springboot.validator;

import java.util.regex.Pattern;

public class Validator {

	/**
	 * No requerido
	 * 
	 * @param parameter Parametro a evaluar
	 * @throws ValidationException
	 */
	public static void notRequired(Object parameter) throws ValidationException {
		if (parameter != null) {
			throw new ValidationException("Parametro no null");
		}
	}

	/**
	 * Parametro String requerido
	 * 
	 * @param parameter Parametro a evaluar
	 * @param regex     Expresion regular
	 * @throws ValidationException
	 */
	public static void requiredString(String parameter, String regex) throws ValidationException {
		if (parameter == null) {
			throw new ValidationException("Parametro null");
		} else {
			if (!Pattern.matches(regex, parameter)) {
				throw new ValidationException("Parametro " + parameter + " no matches " + regex);
			}
		}
	}

	/**
	 * Parametro Long requerido
	 * 
	 * @param parameter Parametro a evaluar
	 * @param regex     Expresion regular
	 * @throws ValidationException
	 */
	public static void requiredLong(Long parameter, String regex) throws ValidationException {
		if (parameter == null) {
			throw new ValidationException("Parametro null");
		} else {
			if (!Pattern.matches(regex, parameter.toString())) {
				throw new ValidationException("Parametro " + parameter + " no matches " + regex);
			}
		}
	}

}
