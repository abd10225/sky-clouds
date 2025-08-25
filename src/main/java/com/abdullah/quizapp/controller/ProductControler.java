package com.abdullah.quizapp.controller;


import com.abdullah.quizapp.model.Products;
import com.abdullah.quizapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductControler {
    @Autowired
    ProductService productService;

    @GetMapping("allProducts")
    public ResponseEntity<List<Products>> getAllProducts() {
        return productService.getAllProducts();
    }


}
