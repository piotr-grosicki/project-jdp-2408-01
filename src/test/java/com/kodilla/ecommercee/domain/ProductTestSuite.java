package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
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
public class ProductTestSuite {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void TestCreateProduct() {
        //Given
        Group group = new Group();
        groupRepository.save(group);
        Product product = new Product("testProduct", 12, new BigDecimal(20));
        product.setGroup(group);

        //When
        productRepository.save(product);
        long id = product.getProductId();
        Optional<Product> createdProduct = productRepository.findById(id);

        //Then
        assertTrue(createdProduct.isPresent());
    }

    @Test
    public void TestGetProduct() {
        //Given
        Group group = new Group();
        groupRepository.save(group);
        long groupId = group.getGroupId();

        Product product = new Product("testProduct", 12, new BigDecimal(20));
        product.setGroup(group);
        productRepository.save(product);

        //When
        Optional<Product> createdProduct = productRepository.findById(product.getProductId());
        long productGroupId = createdProduct.get().getGroup().getGroupId();

        //Then
        assertEquals(productGroupId, groupId);
    }

    @Test
    public void TestUpdateProduct() {
        //Given
        Group group1 = new Group();
        groupRepository.save(group1);
        long group1Id = group1.getGroupId();

        Product product = new Product("testProduct", 12, new BigDecimal(20));
        product.setGroup(group1);
        productRepository.save(product);

        //When
        Group group2 = new Group();
        groupRepository.save(group2);
        long group2Id = group2.getGroupId();
        product.setGroup(group2);
        Product updatedProduct = productRepository.save(product);

        //Then
        assertEquals(group2Id, updatedProduct.getGroup().getGroupId());
    }

    @Test
    public void TestDeleteProduct() {
        //Given
        Group group = new Group();
        groupRepository.save(group);

        Product product = new Product("testProduct", 12, new BigDecimal(20));
        product.setGroup(group);
        productRepository.save(product);

        Order order = new Order();
        order.setProducts(List.of(product));
        orderRepository.save(order);

        //When
        productRepository.deleteById(product.getProductId());
        Optional<Product> deletedProduct = productRepository.findById(product.getProductId());
        Optional<Group> checkedGroup = groupRepository.findById(group.getGroupId());
        Optional<Order> checkedOrder = orderRepository.findById(order.getOrderId());

        //Then
        assertFalse(deletedProduct.isPresent());
        assertTrue(checkedGroup.isPresent());
        assertTrue(checkedOrder.isPresent());
    }
}
