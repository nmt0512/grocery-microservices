package com.thieunm.grocerybase.exception;

import org.springframework.http.HttpStatus;


public interface AbstractError {

    String getMessage();

    int getCode();

    HttpStatus getHttpStatus();
}
