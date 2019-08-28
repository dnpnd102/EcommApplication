package com.example.demo;

import com.example.demo.Utils.JwtTokenUtil;
import com.example.demo.config.JwtRequestFilter;
import com.example.demo.service.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JwtRequestFilterTest{

    @Mock
    JwtUserDetailsService jwtUserDetailsService;

    @InjectMocks
    JwtRequestFilter jwtRequestFilter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doFilterInternalExpireToke() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getHeader("access_token")).thenReturn("Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6InVzZXIiLCJzdWIiOiJhbmFuZCIsImlhdCI6MTU2Njc2MDIyMiwiZXhwIjoxNTY2Nzc4MjIyfQ.WphyTEEv43_0CkgBXHHaFkkNY4ve45O_EZL5S9MVassxHB6xjN-xssdDafA_yb4VXq3vfEuqCtTn_NOrpQNCzw");
        try {
            jwtRequestFilter.doFilterInternal(mockRequest, null, null);
        }catch (IllegalArgumentException e){
        }catch (ExpiredJwtException e){
            Assert.assertTrue("User not found with username: dina".equals(e.getMessage()));
        }catch (Exception e){
        }
    }

    @Test
    public void doFilterInternalValidTokenWithOutBearer() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        String token = JwtTokenUtil.generateToken("anand");
        when(mockRequest.getHeader("access_token")).thenReturn(token);
        try{
            jwtRequestFilter.doFilterInternal(mockRequest, null, null);
        }catch (IllegalArgumentException e){
        }catch (ExpiredJwtException e){
            Assert.assertTrue("User not found with username: dina".equals(e.getMessage()));
        }catch (Exception e){
        }
    }

    @Test
    public void doFilterInternalValidToken() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        String token = JwtTokenUtil.generateToken("anand");
        when(mockRequest.getHeader("access_token")).thenReturn("Bearer "+token);
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("anand");
        Mockito.when(jwtUserDetailsService.loadUserByUsername("anand")).thenReturn(userDetails);
        try{
            jwtRequestFilter.doFilterInternal(mockRequest, null, null);
        }catch (IllegalArgumentException e){
        }catch (ExpiredJwtException e){
            Assert.assertTrue("User not found with username: dina".equals(e.getMessage()));
        }catch (Exception e){
        }
    }

    @Test
    public void doFilterInternalValidTokenNotInDB() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        String token = JwtTokenUtil.generateToken("anand");
        when(mockRequest.getHeader("access_token")).thenReturn("Bearer "+token);
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn(null);
        Mockito.when(jwtUserDetailsService.loadUserByUsername("anand")).thenReturn(userDetails);
        try{
            jwtRequestFilter.doFilterInternal(mockRequest, null, null);
        }catch (IllegalArgumentException e){
        }catch (ExpiredJwtException e){
            Assert.assertTrue("User not found with username: dina".equals(e.getMessage()));
        }catch (Exception e){
        }
    }

    @Test
    public void doFilterInternalValidTokenIllegal() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getHeader("access_token")).thenReturn("Bearer eyJhb");
        try{
            jwtRequestFilter.doFilterInternal(mockRequest, null, null);
        }catch (IllegalArgumentException e){
            Assert.assertTrue("Unable to get JWT Token".equals(e.getMessage()));
        }catch (ExpiredJwtException e){
        }catch (Exception e){
            Assert.assertTrue("JWT strings must contain exactly 2 period characters. Found: 0".equals(e.getMessage()));
        }
    }
}
