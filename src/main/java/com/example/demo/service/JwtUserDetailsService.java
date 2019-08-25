package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.model.persistence.UserProfile;
import com.example.demo.model.persistence.repositories.UserProfileRepository;
import com.example.demo.model.requests.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile userProfile = getProfile(username);
        if(userProfile == null || !StringUtils.isEmpty(userProfile.getPassword())){
            return new User(userProfile.getUsername(), userProfile.getPassword(),
                    new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public UserProfile save(LoginRequest loginRequest) {
        UserProfile newUser = new UserProfile();
        newUser.setUsername(loginRequest.getUsername());
        newUser.setPassword(bcryptEncoder.encode(loginRequest.getPassword()));
        return userProfileRepository.save(newUser);
    }

    public UserProfile getProfile(String userName){
        return userProfileRepository.findByUsername(userName);
    }

}