package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @GetMapping
    public List<ProductDto> getProducts() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{productId}")
    public ProductDto getProduct(Long productId) {
        return new ProductDto(1L, "productName", 3, new BigDecimal(12));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(ProductDto productDto) {

    }

    @PutMapping
    public ProductDto updateProduct(ProductDto productDto) {
        return new ProductDto(1L, "productName", 3, new BigDecimal(12));
    }

    @DeleteMapping
    public void deleteProduct(Long productId) {

    }
}
