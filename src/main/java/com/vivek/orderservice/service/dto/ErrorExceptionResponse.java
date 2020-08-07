package com.vivek.orderservice.service.dto;

import java.io.Serializable;

public class ErrorExceptionResponse implements Serializable {

 private String errorCode;
 private String message;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorExceptionResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
