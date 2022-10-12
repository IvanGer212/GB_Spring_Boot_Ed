package com.geekbrains.ru.gb_spring_boot_ed.repository;

import com.geekbrains.ru.gb_spring_boot_ed.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAllProduct();

    Optional<Product> findProductById(Long id);

    Product  addNewProduct(Product newProduct);
}
