package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
public class CartController {

    @GetMapping
    public List<CartDto> getCarts() {
        List<CartDto> cartDtos = new ArrayList<>();
        cartDtos.add(new CartDto(1L));
        cartDtos.add(new CartDto(2L));
        cartDtos.add(new CartDto(3L));
        return cartDtos;
    }

    @GetMapping(value = "{cartId}")
    public CartDto getCart(@PathVariable Long cartId) {
        return new CartDto(cartId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCart(@RequestBody CartDto cartDto) {
        System.out.println("Cart created with ID: " + cartDto.getCartId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto updateCart(@RequestBody CartDto cartDto) {
        System.out.println("Cart updated with ID: " + cartDto.getCartId());
        return cartDto;
    }


    @DeleteMapping(value = "{cartId}")
    public void deleteCart(@PathVariable Long cartId) {
        System.out.println("Cart deleted with ID: " + cartId);
    }

}
