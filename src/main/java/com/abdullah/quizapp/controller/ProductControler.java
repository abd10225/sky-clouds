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
        List<Products> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Integer id) {
        Products product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Products>> getProductByCategory(@PathVariable String category) {
        List<Products> products = productService.getProductByCategory(category);
        return ResponseEntity.ok(products);
    }

    //search products.
    @GetMapping("")
    public ResponseEntity<List<Products>> searchProducts(@RequestParam String search) {
        List<Products> products = productService.getProductBySearch(search);
        return ResponseEntity.ok(products);
    }



}
