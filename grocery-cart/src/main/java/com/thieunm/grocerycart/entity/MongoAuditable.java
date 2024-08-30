package com.thieunm.grocerycart.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class MongoAuditable<U> {
    @Version
    private Long version;
    @CreatedDate
    @Field("created_date")
    private LocalDateTime createdDate;
    @CreatedBy
    @Field("created_by")
    private U createdBy;
    @LastModifiedDate
    @Field("last_modified_date")
    private LocalDateTime lastModifiedDate;
    @LastModifiedBy
    @Field("last_modified_by")
    private U lastModifiedBy;
}
