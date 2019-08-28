package com.example.demo;

import com.example.demo.controllers.UserController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import jdk.nashorn.internal.runtime.options.Option;
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

import java.util.Optional;

@SpringBootTest
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    UserController userController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findById(){
        Long id = new Long(12);
        User user = new User();
        user.setId(12);
        Optional<User> option = Optional.of(user);
        Mockito.when(userRepository.findById(id)).thenReturn(option);
        ResponseEntity<?> responseEntity = userController.findById(id);
        Assert.assertTrue(200 == responseEntity.getStatusCodeValue());
    }

    @Test
    public void findByUserName(){
        User user = new User();
        user.setId(12);
        Mockito.when(userRepository.findByUsername("dina")).thenReturn(user);
        ResponseEntity<?> responseEntity = userController.findByUserName("dina");
        Assert.assertTrue(200 == responseEntity.getStatusCodeValue());
    }

    @Test
    public void findByUserNameNULL(){
        User user = new User();
        user.setId(12);
        Mockito.when(userRepository.findByUsername("dina")).thenReturn(null);
        ResponseEntity<?> responseEntity = userController.findByUserName("dina");
        Assert.assertTrue(404 == responseEntity.getStatusCodeValue());
    }

    @Test
    public void createUser(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("dina");
        Long id = new Long(12);
        User user = new User();
        user.setId(12);
        Cart cart = new Cart();
        cart.setId(id);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(cartRepository.save(cart)).thenReturn(cart);
        ResponseEntity<?> responseEntity = userController.createUser(createUserRequest);
        Assert.assertTrue(200 == responseEntity.getStatusCodeValue());
    }

}
