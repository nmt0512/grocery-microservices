package com.thieunm.groceryproduct.repository;

import com.thieunm.groceryproduct.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategoryId(Integer categoryId, Pageable pageable);

    List<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query(value = "SELECT p.* " +
            "FROM product p " +
            "JOIN (SELECT category_id, MIN(id) as min_id " +
            "      FROM product " +
            "      GROUP BY category_id " +
            "      LIMIT ?1) grouped " +
            "ON p.id = grouped.min_id", nativeQuery = true)
    List<Product> findTopDistinctByCategory(int size);
}
