package com.vivek.orderservice.web.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.zalando.problem.AbstractThrowableProblem;

import java.net.URI;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)  // 404
public class AlertException extends AbstractThrowableProblem {

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    private final String errorCode;
    private final String message;


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    private final HttpStatus httpStatus;

    public AlertException(HttpStatus httpStatus, String errorCode, String message){
        this(ErrorConstants.DEFAULT_TYPE,httpStatus,errorCode,message);
    }

    public AlertException(URI type, HttpStatus httpStatus, String errorCode, String message){
        super(type,errorCode,null,message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }


}
