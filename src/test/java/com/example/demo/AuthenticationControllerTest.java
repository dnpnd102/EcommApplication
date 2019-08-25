package com.example.demo;

import com.example.demo.controllers.AuthenticationController;
import com.example.demo.model.persistence.JwtRequest;
import com.example.demo.model.persistence.JwtResponse;
import com.example.demo.model.persistence.UserProfile;
import com.example.demo.model.requests.LoginRequest;
import com.example.demo.service.JwtUserDetailsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

@SpringBootTest
public class AuthenticationControllerTest {

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    JwtUserDetailsService jwtUserDetailsService;

    @InjectMocks
    AuthenticationController authenticationController;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveUserProfile(){
        JwtRequest authenticationRequest = new JwtRequest();
        authenticationRequest.setUsername("dina");
        authenticationRequest.setPassword("password");
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername("dina");
        userProfile.setPassword("encodedPass");
        try {
            Mockito.when(jwtUserDetailsService.getProfile("dina")).thenReturn(userProfile);
            ResponseEntity<?> responseEntity = authenticationController.createAuthenticationToken(authenticationRequest);
            Assert.assertTrue(200 == responseEntity.getStatusCodeValue());
        }catch (Exception e){
        }

    }

    @Test
    public void saveUser(){
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setUsername("dina");
        loginRequest.setPassword("pass");
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername("dina");
        userProfile.setPassword("encodedPass");
        try {
            Mockito.when(jwtUserDetailsService.getProfile("dina")).thenReturn(userProfile);
            ResponseEntity<?> responseEntity = authenticationController.saveUser(loginRequest);
            Assert.assertTrue(200 == responseEntity.getStatusCodeValue());
        }catch (Exception e){
        }

    }
}
