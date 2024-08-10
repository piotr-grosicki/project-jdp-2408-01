package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "carts")
public class Cart {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", unique = true, nullable = false)
    private User user;

    @ManyToMany(mappedBy = "carts")
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        product.getCarts().add(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.getCarts().remove(this);
    }
}
