package com.bilgeadam.service.integration.test;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bilgeadam.service.AuthenticationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:spring-data.xml" })
public class AuthenticationServiceIntegrationTest {
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Test
	public void testLoginSuccess() throws Exception{
		UserDetails userDetails = authenticationService.login("admin", "admin");
		assertNotNull(userDetails);
	}
	
	@Test
	public void testUserNameInCorrect() throws Exception{
		UserDetails userDetails = authenticationService.login("admin2", "admin");
		assertNull(userDetails);
	}
	
	@Test
	public void testPasswordInCorrect() throws Exception{
		UserDetails userDetails = authenticationService.login("admin", "admin2");
		assertNull(userDetails);
	}

}
