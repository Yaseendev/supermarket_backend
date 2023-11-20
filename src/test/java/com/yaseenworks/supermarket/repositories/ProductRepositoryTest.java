package com.yaseenworks.supermarket.repositories;

import com.yaseenworks.supermarket.product.entities.*;
import com.yaseenworks.supermarket.product.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    public void insertProduct() {
        Nutrition nutrition = Nutrition.builder().build();
        Category category = Category.builder()
                .name("Test Category")
                .build();

        Product product =
                Product.builder()
                        .name("Test Product")
                        .description("Description")
                        .image("Some Image")
                        .ingredients("Ingredients")
                        .nutrition(nutrition)
                        .category(category)
                        .brand(Brand.builder().name("Test Brand").build())
                        .images(List.of(ProductImage.builder().url("dhdhdh").build()))
                        .rating(4.2)
                        .build();
        repository.save(product);
    }

    @Test
    public void printAllProducts() {

        List<Product> products = repository.findAll();

        System.out.println("Products= " + products);
    }
}