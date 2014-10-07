package com.bilgeadam.service.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bilgeadam.util.PasswordValidator;

public class TestPasswordValidator {

	@Test
	public void testValidLength() {
		assertTrue(PasswordValidator.isValid("Abc123"));
	}

	@Test
	public void testTooShort() {
		assertEquals(false, PasswordValidator.isValid("Abc12"));
	}

	@Test
	public void testNoDigit() {
		assertEquals(false, PasswordValidator.isValid("Abcdef"));
	}

}
