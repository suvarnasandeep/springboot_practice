package com.example.springboot.demo.controller;

import com.example.springboot.demo.entity.Department;
import com.example.springboot.demo.entity.Product;
import com.example.springboot.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public Product saveProduct(@Valid @RequestBody Product product){
        //LOGGER.info("saveDepartment saving...!!!");
        return productService.saveProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getProduct(){
        //LOGGER.info("getDepartment saving...!!!");
        return productService.getProductList();
    }
}
