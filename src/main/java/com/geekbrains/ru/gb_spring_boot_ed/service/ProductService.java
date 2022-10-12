package com.geekbrains.ru.gb_spring_boot_ed.service;

import com.geekbrains.ru.gb_spring_boot_ed.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProduct();

    Optional<Product> findProductById(Long id);

    Product  addNewProduct(Product product);
}
