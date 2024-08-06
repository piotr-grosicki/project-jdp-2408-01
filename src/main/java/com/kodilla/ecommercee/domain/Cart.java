package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "carts")
public class Cart {
    @Id
    @NotNull
    @GeneratedValue
    private Long cartId;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "carts")
    private List<Product> products = new ArrayList<>();
}
