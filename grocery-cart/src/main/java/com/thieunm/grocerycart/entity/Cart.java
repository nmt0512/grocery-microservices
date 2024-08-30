package com.thieunm.grocerycart.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("cart")
public class Cart extends MongoAuditable<String> {
    @Id
    private String id;
    @Field("user_id")
    private String userId;
    @Field("product_id")
    private Integer productId;
    @Field
    private Integer quantity;
    @Field("total_price")
    private Integer totalPrice;
}
