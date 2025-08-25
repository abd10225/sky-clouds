package com.abdullah.quizapp.service;


import com.abdullah.quizapp.dao.ProductDao;
import com.abdullah.quizapp.model.Products;
import com.abdullah.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    public ResponseEntity<List<Products>> getAllProducts() {
        try {
            return new ResponseEntity<>(productDao.findAll(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
