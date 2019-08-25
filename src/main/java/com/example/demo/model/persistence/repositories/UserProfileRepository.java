package com.example.demo.model.persistence.repositories;

import com.example.demo.model.persistence.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByUsername(String username);

}
