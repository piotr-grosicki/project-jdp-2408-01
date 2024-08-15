package com.kodilla.ecommercee.mappers;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductMapper {
    public Product mapToProduct(final ProductDto productDto) {
        return new Product(
                productDto.getProductId(),
                productDto.getName(),
                productDto.getQuantity(),
                productDto.getPrice()
        );
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getProductId(),
                product.getName(),
                product.getQuantity(),
                product.getPrice()
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductDto)
                .toList();
    }
}
