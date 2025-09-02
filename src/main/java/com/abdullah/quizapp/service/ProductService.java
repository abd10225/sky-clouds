package com.abdullah.quizapp.service;



import com.abdullah.quizapp.dao.ProductDao;
import com.abdullah.quizapp.model.Products;
import com.abdullah.quizapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    public List<Products> getAllProducts() {
        List<Products> products = productDao.findAll();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Products are empty");
        }
        return products;
    }

    public Products getProductById(Integer id) {
        return productDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public List<Products> getProductByCategory(String category) {
        List<Products> products = productDao.findByCategory(category);
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No products found in category: " + category);
        }
        return products;
    }

    public List<Products> getProductBySearch(String search) {
        List<Products> products = productDao.getProductBySearch(search);
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No products matched your search: " + search);
        }
        return products;
    }

    public Products createProduct(Products product) {
        return productDao.save(product);
    }

    public Products updateProduct(Integer id, Products updatedProduct) {
        Products existing = productDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        if (updatedProduct.getName() != null) existing.setName(updatedProduct.getName());
        if (updatedProduct.getAmount() != null) existing.setAmount(updatedProduct.getAmount());
        if (updatedProduct.getStock() != null) existing.setStock(updatedProduct.getStock());
        if (updatedProduct.getCategory() != null) existing.setCategory(updatedProduct.getCategory());
        if (updatedProduct.getBrand() != null) existing.setBrand(updatedProduct.getBrand());
        if (updatedProduct.getReorder_level() != null) existing.setReorder_level(updatedProduct.getReorder_level());

        return productDao.save(existing);
    }

    public void deleteProduct(Integer id) {
        if (!productDao.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
        productDao.deleteById(id);
    }


}
