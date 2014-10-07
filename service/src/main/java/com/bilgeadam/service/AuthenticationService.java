package com.bilgeadam.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
	
	public UserDetails login(String userName, String password) throws Exception;

}
