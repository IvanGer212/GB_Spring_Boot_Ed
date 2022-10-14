package com.geekbrains.ru.gb_spring_boot_ed.service.impl;

import com.geekbrains.ru.gb_spring_boot_ed.domain.Product;
import com.geekbrains.ru.gb_spring_boot_ed.repository.ProductRepository;
import com.geekbrains.ru.gb_spring_boot_ed.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
}
