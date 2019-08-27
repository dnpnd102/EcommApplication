package com.example.demo.controllers;

import com.example.demo.SareetaApplication;
import com.example.demo.Utils.JwtTokenUtil;
import com.example.demo.model.persistence.JwtRequest;
import com.example.demo.model.persistence.JwtResponse;
import com.example.demo.model.persistence.UserProfile;
import com.example.demo.model.requests.LoginRequest;
import com.example.demo.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
public class AuthenticationController {

    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        logger.info("authenticationRequest for  authenticate");
        String token = null;
        UserProfile userProfile = userDetailsService.getProfile(authenticationRequest.getUsername());
        if(userProfile != null && !StringUtils.isEmpty(userProfile.getPassword())){
            token = JwtTokenUtil.generateToken(authenticationRequest.getUsername());
            logger.info("token generated succesfully : "+token);
        }
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody @Valid LoginRequest loginRequest) throws Exception {
        logger.info("login request for register"+loginRequest);
        return ResponseEntity.ok(userDetailsService.save(loginRequest));
    }
}
