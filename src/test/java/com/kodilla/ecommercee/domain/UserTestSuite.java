package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class UserTestSuite {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testCreateUser() {
        // Given
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");
        user.setEmail("test@example.com");
        user.setIsActive(true);
        user.setPersonalKey(123456789L);

        // When
        userRepository.save(user);
        long userId = user.getUserId();
        Optional<User> createdUser = userRepository.findById(userId);

        // Then
        assertTrue(createdUser.isPresent());
        assertEquals("testUser", createdUser.get().getUsername());
        assertEquals("password123", createdUser.get().getPassword());
        assertEquals("test@example.com", createdUser.get().getEmail());
        assertTrue(createdUser.get().getIsActive());
        assertEquals(123456789L, createdUser.get().getPersonalKey());
    }

    @Test
    public void testGetUser(){
        // Given
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");
        user.setEmail("test@example.com");
        user.setIsActive(true);
        user.setPersonalKey(123456789L);
        userRepository.save(user);
        long userId = user.getUserId();

        // When
        Optional<User> foundUser = userRepository.findById(userId);

        // Then
        assertTrue(foundUser.isPresent());
        assertEquals("testUser", foundUser.get().getUsername());
        assertEquals("password123", foundUser.get().getPassword());
        assertEquals("test@example.com", foundUser.get().getEmail());
        assertTrue(foundUser.get().getIsActive());
        assertEquals(123456789L, foundUser.get().getPersonalKey());
    }

    @Test
    public void testUpdatingUser(){
        // Given
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");
        user.setEmail("test@example.com");
        user.setIsActive(true);
        user.setPersonalKey(123456789L);
        userRepository.save(user);
        long userId = user.getUserId();

        // When
        user.setUsername("updatedUser");
        user.setEmail("updated@example.com");
        user.setIsActive(false);
        User updatedUser = userRepository.save(user);

        // Then
        assertEquals(userId, updatedUser.getUserId());
        assertEquals("updatedUser", updatedUser.getUsername());
        assertEquals("updated@example.com", updatedUser.getEmail());
        assertFalse(updatedUser.getIsActive());
    }

    @Test
    public void testDeleteUser(){
        // Given
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");
        user.setEmail("test@example.com");
        user.setIsActive(true);
        user.setPersonalKey(123456789L);

        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);

        Order order = new Order(user);
        user.getOrders().add(order);

        userRepository.save(user);
        long userId = user.getUserId();
        long cartId = cart.getCartId();
        long orderId = order.getOrderId();

        // When
        userRepository.deleteById(userId);
        Optional<User> deletedUser = userRepository.findById(userId);
        Optional<Cart> deletedCart = cartRepository.findById(cartId);
        Optional<Order> deletedOrder = orderRepository.findById(orderId);

        // Then
        assertFalse(deletedUser.isPresent());
        assertTrue(deletedCart.isPresent());
        assertTrue(deletedOrder.isPresent());
    }
}
