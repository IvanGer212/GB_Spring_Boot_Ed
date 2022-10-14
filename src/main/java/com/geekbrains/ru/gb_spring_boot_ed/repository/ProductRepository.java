package com.geekbrains.ru.gb_spring_boot_ed.repository;

import com.geekbrains.ru.gb_spring_boot_ed.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCostIsLessThan (int cost);

    List<Product> findAllByCostGreaterThan (int cost);

    List<Product> findAllByCostBetween(int min, int max );

}
