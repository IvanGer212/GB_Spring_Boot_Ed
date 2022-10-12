package com.geekbrains.ru.gb_spring_boot_ed.repository.impl;

import com.geekbrains.ru.gb_spring_boot_ed.domain.Product;
import com.geekbrains.ru.gb_spring_boot_ed.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> products;
    private Long curProductNum = 0L;

    @PostConstruct
    void init(){
        products = new ArrayList<>();
        for (int i =1; i<=10; i++){
            curProductNum++;
            products.add(new Product(curProductNum,"Product "+curProductNum, 20+i ));
        }
    }

    @Override
    public List<Product> getAllProduct() {
        return products;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return products.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public Product addNewProduct(Product newProduct) {
        Optional<Product> maybeExistingProduct = products.stream().filter(p -> p.getTitle().equals(newProduct.getTitle())).findFirst();
        if(maybeExistingProduct.isPresent()){
            Product product = maybeExistingProduct.get();
            product.setTitle(newProduct.getTitle());
            product.setCost(newProduct.getCost());
        }
        else {
            curProductNum++;
            newProduct.setId(curProductNum);
            products.add(newProduct);
        }
        return newProduct;
    }
}
