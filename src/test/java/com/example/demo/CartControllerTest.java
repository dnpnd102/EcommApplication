package com.example.demo;

import com.example.demo.controllers.AuthenticationController;
import com.example.demo.controllers.CartController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
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

import java.util.Optional;

@SpringBootTest
public class CartControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    CartController cartController;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addTocart(){
        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(12);
        modifyCartRequest.setUsername("dina");
        User user = new User();
        user.setId(12);
        Cart cart = new Cart();
        cart.setId(new Long(12));
        Item item = new Item();
        item.setId(new Long(1233));
        Optional<Item> optional = Optional.of(item);
        Mockito.when(userRepository.findByUsername("dina")).thenReturn(user);
        Mockito.when(itemRepository.findById(modifyCartRequest.getItemId())).thenReturn(optional);
        Mockito.when(cartRepository.save(cart)).thenReturn(cart);
        ResponseEntity<Cart> responseEntity = cartController.addTocart(modifyCartRequest);
        Assert.assertTrue(200 == responseEntity.getStatusCodeValue());
    }

    @Test
    public void addTocartNullUser(){
        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(12);
        modifyCartRequest.setUsername("dina");
        User user = new User();
        user.setId(12);
        Cart cart = new Cart();
        cart.setId(new Long(12));
        Item item = new Item();
        item.setId(new Long(1233));
        Optional<Item> optional = Optional.of(item);
        Mockito.when(userRepository.findByUsername("dina")).thenReturn(null);
        ResponseEntity<Cart> responseEntity = cartController.addTocart(modifyCartRequest);
        Assert.assertTrue(404 == responseEntity.getStatusCodeValue());
    }

    @Test
    public void addTocartNullUserNoItems(){
        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(12);
        modifyCartRequest.setUsername("dina");
        User user = new User();
        user.setId(12);
        Cart cart = new Cart();
        cart.setId(new Long(12));
        Item item = new Item();
        item.setId(new Long(1233));
        Optional<Item> optional = Optional.empty();
        Mockito.when(userRepository.findByUsername("dina")).thenReturn(user);
        Mockito.when(itemRepository.findById(modifyCartRequest.getItemId())).thenReturn(optional);
        Mockito.when(cartRepository.save(cart)).thenReturn(cart);
        ResponseEntity<Cart> responseEntity = cartController.addTocart(modifyCartRequest);
        Assert.assertTrue(404 == responseEntity.getStatusCodeValue());
    }

    @Test
    public void removeFromcart(){
        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(12);
        modifyCartRequest.setUsername("dina");
        User user = new User();
        user.setId(12);
        Cart cart = new Cart();
        cart.setId(new Long(12));
        Item item = new Item();
        item.setId(new Long(1233));
        Optional<Item> optional = Optional.of(item);
        Mockito.when(userRepository.findByUsername("dina")).thenReturn(user);
        Mockito.when(itemRepository.findById(modifyCartRequest.getItemId())).thenReturn(optional);
        Mockito.when(cartRepository.save(cart)).thenReturn(cart);
        ResponseEntity<Cart> responseEntity = cartController.removeFromcart(modifyCartRequest);
        Assert.assertTrue(200 == responseEntity.getStatusCodeValue());
    }

    @Test
    public void removeFromcartNullUser(){
        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(12);
        modifyCartRequest.setUsername("dina");
        User user = new User();
        user.setId(12);
        Cart cart = new Cart();
        cart.setId(new Long(12));
        Item item = new Item();
        item.setId(new Long(1233));
        Optional<Item> optional = Optional.of(item);
        Mockito.when(userRepository.findByUsername("dina")).thenReturn(null);
        Mockito.when(itemRepository.findById(modifyCartRequest.getItemId())).thenReturn(optional);
        Mockito.when(cartRepository.save(cart)).thenReturn(cart);
        ResponseEntity<Cart> responseEntity = cartController.removeFromcart(modifyCartRequest);
        Assert.assertTrue(404 == responseEntity.getStatusCodeValue());
    }

    @Test
    public void removeFromcartWithNOItem(){
        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(12);
        modifyCartRequest.setUsername("dina");
        User user = new User();
        user.setId(12);
        Cart cart = new Cart();
        cart.setId(new Long(12));
        Item item = new Item();
        item.setId(new Long(1233));
        Optional<Item> optional = Optional.empty();
        Mockito.when(userRepository.findByUsername("dina")).thenReturn(user);
        Mockito.when(itemRepository.findById(modifyCartRequest.getItemId())).thenReturn(optional);
        Mockito.when(cartRepository.save(cart)).thenReturn(cart);
        ResponseEntity<Cart> responseEntity = cartController.removeFromcart(modifyCartRequest);
        Assert.assertTrue(404 == responseEntity.getStatusCodeValue());
    }

}
