package com.geekbrains.ru.gb_spring_boot_ed.exception;


public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
