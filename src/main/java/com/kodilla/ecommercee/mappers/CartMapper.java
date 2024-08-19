package com.kodilla.ecommercee.mappers;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartMapper {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;

    public Cart mapToCart(final CartDto cartDto) {
        return new Cart(
                cartDto.getCartId(),
                userMapper.mapToUser(cartDto.getUser()),
                productMapper.mapToProductList(cartDto.getProducts())
        );
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(
                cart.getCartId(),
                userMapper.mapToUserDto(cart.getUser()),
                productMapper.mapToProductDtoList(cart.getProducts())
        );
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> cartList) {
        return cartList.stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
    }
}
