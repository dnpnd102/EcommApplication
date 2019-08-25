package com.example.demo;

import com.example.demo.model.persistence.UserProfile;
import com.example.demo.model.persistence.repositories.UserProfileRepository;
import com.example.demo.model.requests.LoginRequest;
import com.example.demo.service.JwtUserDetailsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class JwtUserDetailServiceTest {

    @Mock
    UserProfileRepository userProfileRepository;

    @InjectMocks
    JwtUserDetailsService jwtUserDetailsService;

    @Mock
    PasswordEncoder bcryptEncoder;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveUserProfile(){
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setUsername("dina");
        loginRequest.setPassword("pass");
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername("dina");
        Mockito.when(bcryptEncoder.encode("pass")).thenReturn("encodedPass");
        userProfile.setPassword("encodedPass");
        Mockito.when(userProfileRepository.save(Mockito.any())).thenReturn(userProfile);
        UserProfile result = jwtUserDetailsService.save(loginRequest);
        Assert.assertTrue(result.getUsername().equals(loginRequest.getUsername()) && result.getPassword().equals("encodedPass"));
    }

    @Test
    public void loadUserByUsername(){
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername("dina");
        userProfile.setPassword("encodedPass");
        Mockito.when(userProfileRepository.findByUsername(Mockito.any())).thenReturn(userProfile);
        UserDetails result = jwtUserDetailsService.loadUserByUsername("dina");
        Assert.assertTrue(result.getUsername().equals(userProfile.getUsername()) && result.getPassword().equals("encodedPass"));
    }

    @Test
    public void loadUserByUsernameException(){
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername("dina");
        Mockito.when(userProfileRepository.findByUsername(Mockito.any())).thenReturn(userProfile);
        try {
            UserDetails result = jwtUserDetailsService.loadUserByUsername("dina");
        }catch (Exception e){
            Assert.assertTrue("User not found with username: dina".equals(e.getMessage()));
        }
    }


}
