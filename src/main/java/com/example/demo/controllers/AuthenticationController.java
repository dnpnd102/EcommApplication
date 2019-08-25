package com.example.demo.controllers;

import com.example.demo.Utils.JwtTokenUtil;
import com.example.demo.model.persistence.JwtRequest;
import com.example.demo.model.persistence.JwtResponse;
import com.example.demo.model.persistence.UserProfile;
import com.example.demo.model.requests.LoginRequest;
import com.example.demo.service.JwtUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AuthenticationController {

    Logger logger = LoggerFactory.getLogger("splunk.secure.logger");

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        logger.info("jwtRequest"+authenticationRequest.toString());
        String token = null;
        UserProfile userProfile = userDetailsService.getProfile(authenticationRequest.getUsername());
        if(userProfile != null && !StringUtils.isEmpty(userProfile.getPassword())){
            token = JwtTokenUtil.generateToken(authenticationRequest.getUsername());
        }
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody LoginRequest loginRequest) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(loginRequest));
    }
}
