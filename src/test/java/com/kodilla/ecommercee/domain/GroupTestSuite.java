package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class GroupTestSuite {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testCreateGroup() {
        //Given
        Group group = new Group();
        group.setName("testGroup");

        //When
        groupRepository.save(group);
        Long id = group.getGroupId();
        Optional<Group> createdGroup = groupRepository.findById(id);

        //Then
        assertTrue(createdGroup.isPresent());
        assertEquals("testGroup", createdGroup.get().getName());
    }

    @Test
    public void testGetGroup() {
        //Given
        Group group = new Group();
        group.setName("testGroup");
        groupRepository.save(group);

        //When
        Optional<Group> createdGroup = groupRepository.findById(group.getGroupId());
        String name = createdGroup.get().getName();

        //Then
        assertEquals("testGroup", name);
    }

    @Test
    public void testUpdateGroup() {
        //Given
        Group group = new Group();
        group.setName("testGroup");
        groupRepository.save(group);

        //When
        group.setName("updatedGroupName");
        groupRepository.save(group);
        Optional<Group> updatedGroup = groupRepository.findById(group.getGroupId());

        //Then
        assertEquals("updatedGroupName", updatedGroup.get().getName());
    }

    @Test
    public void testDeleteGroup() {
        //Given
        Group group = new Group();
        groupRepository.save(group);

        Product product = new Product();
        product.setGroup(group);
        productRepository.save(product);

        //When
        groupRepository.deleteById(group.getGroupId());
        Optional<Group> checkedGroup = groupRepository.findById(group.getGroupId());
        Optional<Product> checkedProduct = productRepository.findById(product.getProductId());

        //Then
        assertFalse(checkedGroup.isPresent());
        assertTrue(checkedProduct.isPresent());
    }
}
