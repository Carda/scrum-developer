package com.bilgeadam.util;

import java.util.regex.Pattern;

public class PasswordValidator {

	public static final int MIN = 6;
	public static final int MAX = 10;

	public static boolean isValid(String password) {
		return containsDigit(password) && isValidLength(password);
	}

	public static boolean containsDigit(String password) {
		return Pattern.matches(".*\\p{Digit}.*", password);
	}

	public static boolean isValidLength(String password) {
		//TODO implement length check
		if(password == null || password.isEmpty())
			return false;
		if(password.length() < MIN)
			return false;
		if(password.length() > MAX)
			return false;
			
		return true;
	}

}
