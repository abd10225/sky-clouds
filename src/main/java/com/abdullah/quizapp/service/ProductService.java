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
}
