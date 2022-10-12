package com.geekbrains.ru.gb_spring_boot_ed.controller;

import com.geekbrains.ru.gb_spring_boot_ed.domain.Product;
import com.geekbrains.ru.gb_spring_boot_ed.exception.ErrorResponse;
import com.geekbrains.ru.gb_spring_boot_ed.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public String getProducts(Model model){
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products",products);
        return "products";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable Long id){
        return productService.findProductById(id).get();
    }

    @GetMapping("/create-product")
    public String createAddProductPage(Model model){
        model.addAttribute("newProduct", new Product());

        return "create-product";
    }
    @PostMapping
    public String addProduct(@ModelAttribute("newProduct") Product newProduct, Model model) {
        Optional<ErrorResponse> validationError = validationNewProduct(newProduct);
        if(validationError.isPresent()){
            model.addAttribute("error", validationError.get());
            return "exception-page";
        }
        productService.addNewProduct(newProduct);
        return "redirect:/product";
    }


    private Optional<ErrorResponse> validationNewProduct(Product newProduct){
        List<String> details = new ArrayList<>();
        if (newProduct.getTitle().isEmpty()){
            details.add("Product name could not be empty!");
        }
        if (newProduct.getCost() <= 0){
            details.add("Price could not be less or equal 0!");
        }
        if (details.size()!=0){
            return Optional.of(new ErrorResponse("Uncorrect Product!",details));
        }
        return Optional.empty();
    }
}

