package com.plant.common.model;

import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Service Exception.
 *
 * @author Anatolii Hamza
 */
public class ServiceException extends Exception {

    private final HttpStatus responseCode;
    private final ServiceResponseBody responseBody;

    public ServiceException(HttpStatus responseCode, ServiceResponseBody responseBody) {
        super(responseBody.getMessage());
        this.responseCode = responseCode;
        this.responseBody = responseBody;
    }

    public ServiceException(HttpStatus responseCode, String errorCode, String message) {
        super(message);
        this.responseCode = responseCode;
        this.responseBody = new ServiceResponseBody(message, errorCode);
    }

    public ServiceException(HttpStatus responseCode, String errorCode, String message, Map<String, String> data) {
        super(message);
        this.responseCode = responseCode;
        this.responseBody = new ServiceResponseBody(message, errorCode, data);
    }

    public HttpStatus getCode() {
        return responseCode;
    }

    public ServiceResponseBody getBody() {
        return responseBody;
    }
}
