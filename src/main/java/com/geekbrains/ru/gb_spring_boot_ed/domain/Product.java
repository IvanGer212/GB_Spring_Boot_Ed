package com.geekbrains.ru.gb_spring_boot_ed.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private Integer cost;
}
