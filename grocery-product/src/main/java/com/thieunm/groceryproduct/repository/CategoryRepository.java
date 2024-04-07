package com.thieunm.groceryproduct.repository;

import com.thieunm.groceryproduct.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
