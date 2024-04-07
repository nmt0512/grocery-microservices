package com.thieunm.groceryproduct.repository;

import com.thieunm.groceryproduct.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
