package com.bilgeadam.service.security.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.security.MessageDigest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import com.bilgeadam.service.AuthenticationServiceImpl;
import com.bilgeadam.service.CustomUserDetailsService;
import com.bilgeadam.util.PasswordValidator;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AuthenticationServiceImpl.class, MessageDigest.class, AuthenticationServiceTest.class})
public class AuthenticationServiceTest {
	
	@Mock
	CustomUserDetailsService userDetailsService;
	
	@Mock
	UserDetails userDetails;
	
	@Mock  
	MessageDigest messageDigestMock;
	
	@Mock
	PasswordValidator passwordValidator;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(AuthenticationServiceTest.class);
	}

	
	@Test
	public void testLoginFailUsernameIncorrect() throws Exception{
		AuthenticationServiceImpl authenticationService = new AuthenticationServiceImpl();
		when(userDetailsService.loadUserByUsername(anyString())).thenReturn(null);
		ReflectionTestUtils.setField(authenticationService, "userDetailsService", userDetailsService);
		UserDetails userDetails = authenticationService.login("admin2", "admin");
		setupMessageDigest();
        when(messageDigestMock.digest(Matchers.<byte[]>anyObject())).thenReturn("hashed_value".getBytes());
        assertNull(userDetails);
	}
	
	@Test
	public void testLoginFailPasswordIncorrect() throws Exception{
		AuthenticationServiceImpl authenticationService = new AuthenticationServiceImpl();
		when(userDetailsService.loadUserByUsername(anyString())).thenReturn(null);
		ReflectionTestUtils.setField(authenticationService, "userDetailsService", userDetailsService);
		when(userDetailsService.loadUserByUsername("admin")).thenReturn(userDetails);
		when(this.userDetails.getPassword()).thenReturn("abc");
		UserDetails userDetails = authenticationService.login("admin", "admin2");
		setupMessageDigest();
        when(messageDigestMock.digest(Matchers.<byte[]>anyObject())).thenReturn("hashed_value".getBytes());
        PowerMockito.whenNew(PasswordValidator.class).withNoArguments().thenReturn(passwordValidator);
        assertNull(userDetails);
	}
	
	
	@Test
	public void testLoginSuccess() throws Exception{
		AuthenticationServiceImpl authenticationService = new AuthenticationServiceImpl();
		ReflectionTestUtils.setField(authenticationService, "userDetailsService", userDetailsService);
		when(userDetailsService.loadUserByUsername("admin")).thenReturn(userDetails);
		when(this.userDetails.getPassword()).thenReturn("21232f297a57a5a743894a0e4a801fc3");
		UserDetails userDetails = authenticationService.login("admin", "admin");
		setupMessageDigest();
        assertNotNull(userDetails);
	}
	
	public void setupMessageDigest() throws Exception {
        PowerMockito.mockStatic(MessageDigest.class);
        when(MessageDigest.getInstance("MD5")).thenReturn(messageDigestMock);
        Mockito.doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                return "called with arguments: " + args;
            }
        }).when(messageDigestMock).update((byte[])anyObject(), anyInt(), anyInt());
 
    }


}
