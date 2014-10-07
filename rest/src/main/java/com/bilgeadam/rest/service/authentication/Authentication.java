package com.bilgeadam.rest.service.authentication;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bilgeadam.service.AuthenticationService;

@Component
@Path("/authentication")
public class Authentication {

	@Context
	ServletContext sc;

	@GET
	@Path("/login")
	public Response login(@QueryParam("username") String userName,
			@QueryParam("password") String password) {
		org.springframework.web.context.WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(sc);

		AuthenticationService authenticationService = (AuthenticationService) context
				.getBean("authenticationService");
		
		Boolean loggedIn = false;
		
		UserDetails userDetails;
		try {
			userDetails = authenticationService.login(userName, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
		if(userDetails != null){
			loggedIn = true;
		}
		return Response.status(200).entity(loggedIn.toString()).build();
	}

}
