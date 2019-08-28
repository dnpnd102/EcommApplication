package com.example.demo;

import com.example.demo.controllers.OrderController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    OrderController orderController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void submit(){
        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(12);
        modifyCartRequest.setUsername("dina");
        User user = new User();
        user.setId(12);
        Cart cart = new Cart();
        cart.setId(new Long(12));
        Item item = new Item();
        item.setDescription("item is hot");
        List<Item> items = new ArrayList<>();
        items.add(item);
        cart.setItems(items);
        user.setCart(cart);
        UserOrder userOrder = new UserOrder();
        userOrder.setId(new Long(12));
        userOrder.setUser(user);

        Mockito.when(userRepository.findByUsername("dina")).thenReturn(user);
        Mockito.when(orderRepository.save(userOrder)).thenReturn(userOrder);
        ResponseEntity<UserOrder> responseEntity = orderController.submit("dina");
        Assert.assertTrue(200 == responseEntity.getStatusCodeValue());
    }

    @Test
    public void submitNULL(){
        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(12);
        modifyCartRequest.setUsername("dina");
        User user = new User();
        user.setId(12);
        Cart cart = new Cart();
        cart.setId(new Long(12));
        Item item = new Item();
        item.setDescription("item is hot");
        List<Item> items = new ArrayList<>();
        items.add(item);
        cart.setItems(items);
        user.setCart(cart);
        UserOrder userOrder = new UserOrder();
        userOrder.setId(new Long(12));
        userOrder.setUser(user);

        Mockito.when(userRepository.findByUsername("dina")).thenReturn(null);
        Mockito.when(orderRepository.save(userOrder)).thenReturn(userOrder);
        ResponseEntity<UserOrder> responseEntity = orderController.submit("dina");
        Assert.assertTrue(404 == responseEntity.getStatusCodeValue());
    }

    @Test
    public void getOrdersForUser(){
        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(12);
        modifyCartRequest.setUsername("dina");
        User user = new User();
        user.setId(12);
        Cart cart = new Cart();
        cart.setId(new Long(12));
        Item item = new Item();
        item.setDescription("item is hot");
        List<Item> items = new ArrayList<>();
        items.add(item);
        cart.setItems(items);
        user.setCart(cart);
        UserOrder userOrder = new UserOrder();
        userOrder.setId(new Long(12));
        userOrder.setUser(user);
        List<UserOrder> userOrders = new ArrayList<>();
        Mockito.when(userRepository.findByUsername("dina")).thenReturn(user);
        Mockito.when(orderRepository.findByUser(user)).thenReturn(userOrders);
        ResponseEntity<List<UserOrder>> responseEntity = orderController.getOrdersForUser("dina");
        Assert.assertTrue(200 == responseEntity.getStatusCodeValue());
    }

    @Test
    public void getOrdersForUserNULL(){
        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(12);
        modifyCartRequest.setUsername("dina");
        User user = new User();
        user.setId(12);
        Cart cart = new Cart();
        cart.setId(new Long(12));
        Item item = new Item();
        item.setDescription("item is hot");
        List<Item> items = new ArrayList<>();
        items.add(item);
        cart.setItems(items);
        user.setCart(cart);
        UserOrder userOrder = new UserOrder();
        userOrder.setId(new Long(12));
        userOrder.setUser(user);
        List<UserOrder> userOrders = new ArrayList<>();
        Mockito.when(userRepository.findByUsername("dina")).thenReturn(null);
        Mockito.when(orderRepository.findByUser(user)).thenReturn(userOrders);
        ResponseEntity<List<UserOrder>> responseEntity = orderController.getOrdersForUser("dina");
        Assert.assertTrue(404 == responseEntity.getStatusCodeValue());
    }

}
