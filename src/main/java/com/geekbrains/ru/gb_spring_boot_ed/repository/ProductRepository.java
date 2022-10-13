package com.geekbrains.ru.gb_spring_boot_ed.repository;

import com.geekbrains.ru.gb_spring_boot_ed.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
