package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "carts")
public class Cart {
    @Id
    @NonNull
    @GeneratedValue
    private Long cartId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", unique = true, nullable = false)
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "carts")
    private List<Product> products = new ArrayList<>();
}
