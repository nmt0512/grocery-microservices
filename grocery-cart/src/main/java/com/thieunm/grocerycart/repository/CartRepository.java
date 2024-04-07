package com.thieunm.grocerycart.repository;

import com.thieunm.grocerycart.entity.Cart;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

    Optional<Cart> findByUserIdAndProductId(String userId, Integer productId);

    List<Cart> findByUserId(String userId, Sort sort);
}
