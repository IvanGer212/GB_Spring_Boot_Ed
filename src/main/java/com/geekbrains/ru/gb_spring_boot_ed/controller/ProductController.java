package com.geekbrains.ru.gb_spring_boot_ed.controller;

import com.geekbrains.ru.gb_spring_boot_ed.domain.Limits;
import com.geekbrains.ru.gb_spring_boot_ed.domain.Product;
import com.geekbrains.ru.gb_spring_boot_ed.exception.ErrorResponse;
import com.geekbrains.ru.gb_spring_boot_ed.exception.ResourceNotFoundException;
import com.geekbrains.ru.gb_spring_boot_ed.service.ProductService;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.ResponseEntity;
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
        model.addAttribute("Limits", new Limits());
        return "products";
    }

    @GetMapping("/product-less")
    public String getProductLess (@RequestParam (defaultValue = "1000000000")int cost, Model model){
        List<Product> allByCostIsLessThan = productService.findAllByCostIsLessThan(cost);
        model.addAttribute("products", allByCostIsLessThan);
        return "products";
    }

    @GetMapping("/product-greater")
    public String getProductGreater(@RequestParam (defaultValue = "0") int cost, Model model){
        List<Product> allByCostGreaterThan = productService.findAllByCostGreaterThan(cost);
        model.addAttribute("products", allByCostGreaterThan);
        return "products";
    }

    @GetMapping("/product-between")
    public String getProductBetweenCost(@RequestParam (defaultValue = "0") int min, @RequestParam(defaultValue = "1000000") int max, Model model){
        List<Product> allByCostBetween = productService.findAllByCostBetween(min, max);
        model.addAttribute("products", allByCostBetween);
        model.addAttribute("Limits", new Limits());
        return "products";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable Long id){
        return productService.findProductById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found! id= "+id));
    }

    @GetMapping("/create-product")
    public String createAddProductPage(Model model){
        model.addAttribute("newProduct", new Product());

        return "create-product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Optional<Product> productById = productService.findProductById(id);
        if (productById.isPresent()){
        productService.deleteProductById(id);
        return "redirect:/product";}
        else {
            throw new ResourceNotFoundException("Product could not be delete. Product not found. id=" + id);
        }
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


    private Optional<ErrorResponse> validationNewProduct (Product newProduct){
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

