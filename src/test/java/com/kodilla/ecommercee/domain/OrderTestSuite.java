package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
public class OrderTestSuite {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void testCreateOrder() {
        //Given
        User user = new User();
        userRepository.save(user);

        Order order = new Order(user);

        //When
        orderRepository.save(order);
        long id = order.getOrderId();
        Optional<Order> createdOrder = orderRepository.findById(id);

        //Then
        assertTrue(createdOrder.isPresent());
    }

    @Test
    public void testGetOrder() {
        //Given
        User user = new User();
        userRepository.save(user);
        long userId = user.getUserId();

        Order order = new Order(user);
        orderRepository.save(order);

        //When
        Optional<Order> createdOrder = orderRepository.findById(order.getOrderId());
        long orderUserId = createdOrder.get().getUser().getUserId();

        //Then
        assertEquals(userId, orderUserId);
    }

    @Test
    public void testUpdatingOrder() {
        //Given
        User user1 = new User();
        userRepository.save(user1);
        //long userId = user1.getUserId();

        Order order = new Order(user1);
        orderRepository.save(order);

        //When
        User user2 = new User();
        userRepository.save(user2);
        long newUserId = user2.getUserId();
        order.setUser(user2);
        Order updatedOrder = orderRepository.save(order);

        //Then
        assertEquals(newUserId, updatedOrder.getUser().getUserId());
    }

    @Test
    public void testDeleteOrder() {
        //Given
        User user = new User();
        userRepository.save(user);
        long userId = user.getUserId();

        Group group = new Group();
        group.setName("testGroup");
        groupRepository.save(group);

        Product product = new Product("testName", 12, new BigDecimal(30));
        product.setGroup(group);
        productRepository.save(product);

        Order order = new Order();
        order.setUser(user);
        order.setProducts(List.of(product));
        orderRepository.save(order);

        //When
        orderRepository.deleteById(order.getOrderId());
        Optional<Order> checkDeletedOrder = orderRepository.findById(order.getOrderId());
        Optional<User> checkUser = userRepository.findById(userId);
        Optional<Product> checkProduct = productRepository.findById(product.getProductId());

        //Then
        assertFalse(checkDeletedOrder.isPresent());
        assertTrue(checkUser.isPresent());
        assertTrue(checkProduct.isPresent());
    }
}
