package com.example.demo;

import com.example.demo.controllers.ItemController;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.ItemRepository;
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
public class ItemControllerTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    ItemController itemController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getItems(){
        Item item = new Item();
        item.setDescription("item is hot");
        List<Item> items = new ArrayList<>();
        items.add(item);
        Mockito.when(itemRepository.findAll()).thenReturn(items);
        ResponseEntity<List<Item>> responseEntity = itemController.getItems();
        Assert.assertTrue(200 == responseEntity.getStatusCodeValue());
    }

    @Test
    public void getItemById(){
        Item item = new Item();
        item.setDescription("item is hot");
        List<Item> items = new ArrayList<>();
        items.add(item);
        Long id = new Long(1234);
        Optional<Item> option = Optional.of(item);
        Mockito.when(itemRepository.findById(id)).thenReturn(option);
        ResponseEntity<Item> responseEntity = itemController.getItemById(id);
        Assert.assertTrue(200 == responseEntity.getStatusCodeValue());
    }

    @Test
    public void getItemsByName(){
        Item item = new Item();
        item.setDescription("item is hot");
        List<Item> items = new ArrayList<>();
        items.add(item);
        Mockito.when(itemRepository.findByName("item")).thenReturn(items);
        ResponseEntity<List<Item>> responseEntity = itemController.getItemsByName("item");
        Assert.assertTrue(200 == responseEntity.getStatusCodeValue());
    }

    @Test
    public void getItemsByNameNULL(){
        Item item = new Item();
        item.setDescription("item is hot");
        List<Item> items = new ArrayList<>();
        items.add(item);
        Mockito.when(itemRepository.findByName("item")).thenReturn(null);
        ResponseEntity<List<Item>> responseEntity = itemController.getItemsByName("item");
        Assert.assertTrue(404 == responseEntity.getStatusCodeValue());
    }

}
