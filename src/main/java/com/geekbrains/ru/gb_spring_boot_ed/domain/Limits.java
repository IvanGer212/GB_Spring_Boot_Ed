package com.geekbrains.ru.gb_spring_boot_ed.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Limits {
    private Integer min;
    private Integer max;
}
