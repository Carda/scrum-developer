package com.bilgeadam.soap.service;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bilgeadam.service.AuthenticationService;

@WebService
@SOAPBinding(style = Style.RPC)
public class AuthenticationWs {

	@Resource
	private WebServiceContext wsContext;

	@WebMethod
	@WebResult(name = "LoginResponse")
	public Boolean login(@WebParam(name = "userName") String userName,
			@WebParam(name = "password") String password) {

		AuthenticationService authenticationService = (AuthenticationService) getWebAppContext()
				.getBean("authenticationService");

		Boolean loggedIn = false;

		UserDetails userDetails;
		try {
			userDetails = authenticationService.login(userName,
					password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if (userDetails != null) {
			loggedIn = true;
		}
		return loggedIn;

	}

	private WebApplicationContext getWebAppContext() {
		ServletContext servletContext = (ServletContext) wsContext
				.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		WebApplicationContext webContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		return webContext;
	}
}
