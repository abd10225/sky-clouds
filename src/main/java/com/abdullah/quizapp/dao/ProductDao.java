package com.abdullah.quizapp.dao;

import com.abdullah.quizapp.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Products, Integer> {
    List<Products> findByCategory(String category);

    List<Products> findByNameContainingIgnoreCase(String search);

    @Query("SELECT p FROM Products p WHERE " +
            "CAST(p.product_id AS string) LIKE CONCAT('%', :search, '%') OR " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.brand) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "CAST(p.amount AS string) LIKE CONCAT('%', :search, '%') OR " +
            "CAST(p.stock AS string) LIKE CONCAT('%', :search, '%') OR " +
            "CAST(p.createdAt AS string) LIKE CONCAT('%', :search, '%') OR " +
            "CAST(p.updatedAt AS string) LIKE CONCAT('%', :search, '%')")
    List<Products> getProductBySearch( String search);




//    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
//    List<Question> findRandomQuestionByCategory(String category, int numQ);
}
