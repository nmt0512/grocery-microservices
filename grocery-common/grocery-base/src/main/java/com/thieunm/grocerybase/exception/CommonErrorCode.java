package com.thieunm.grocerybase.exception;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum CommonErrorCode implements AbstractError {
    PRODUCT_OUT_OF_STOCK(
            422,
            "Product is out of stock",
            HttpStatus.BAD_REQUEST);

    private final int code;

    private final String message;

    private final HttpStatus httpStatus;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}