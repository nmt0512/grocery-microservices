package com.thieunm.grocerybase.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {

    public <T> ResponseEntity<BaseResponse<T>> success(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setSuccess(true);
        response.setCode(HttpStatus.OK.value());
        response.setData(data);
        return ResponseEntity.ok(response);
    }
}
