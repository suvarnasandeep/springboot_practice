package com.example.springboot.demo.service;

import com.example.springboot.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Product saveProduct(Product product);

    public List<Product> getProductList();
}
