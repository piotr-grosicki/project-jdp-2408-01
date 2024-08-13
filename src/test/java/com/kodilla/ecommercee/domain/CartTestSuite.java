package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
public class CartTestSuite {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateCart() {
        // Given
        User user = new User();
        userRepository.save(user);

        Cart cart = new Cart(user);

        // When
        cartRepository.save(cart);
        long id = cart.getCartId();
        Optional<Cart> createdCart = cartRepository.findById(id);

        // Then
        assertTrue(createdCart.isPresent());
    }

    @Test
    public void testGetCart() {
        // Given
        User user = new User();
        userRepository.save(user);
        long userId = user.getUserId();

        Cart cart = new Cart(user);
        cartRepository.save(cart);

        // When
        Optional<Cart> createdCart = cartRepository.findById(cart.getCartId());
        long cartUserId = createdCart.get().getUser().getUserId();

        // Then
        assertEquals(userId, cartUserId);

    }

    @Test
    public void testUpdateCart() {
        // Given
        User user1 = new User();
        userRepository.save(user1);

        Cart cart = new Cart(user1);
        cartRepository.save(cart);

        // When
        User user2 = new User();
        userRepository.save(user2);
        long newUserId = user2.getUserId();
        cart.setUser(user2);
        Cart updatedCart = cartRepository.save(cart);

        // Then
        assertEquals(newUserId, updatedCart.getUser().getUserId());
    }

    @Test
    public void testDeleteCart() {
        // Given
        User user = new User();
        userRepository.save(user);
        long userId = user.getUserId();

        Cart cart = new Cart(user);
        cartRepository.save(cart);

        // When
        cartRepository.deleteById(cart.getCartId());
        Optional<Cart> checkedDeletedCart = cartRepository.findById(cart.getCartId());
        Optional<User> checkedUser = userRepository.findById(userId);

        // Then
        assertFalse(checkedDeletedCart.isPresent());
        assertTrue(checkedUser.isPresent());

    }

}
