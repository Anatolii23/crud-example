package com.plant.api.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.plant.common.model.ServiceResponseBody;
import com.plant.common.model.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

import static com.plant.common.enums.ApplicationException.CONSTRAINTS_VIOLATION;
import static com.plant.common.enums.ApplicationException.INVALID_REQUEST_ARGUMENT;
import static com.plant.common.enums.ApplicationException.JSON_PROCESSING;
import static com.plant.common.enums.ApplicationException.MESSAGE_NOT_READABLE;
import static com.plant.common.enums.ApplicationException.METHOD_ARGUMENT_NOT_VALID;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Application Exception Handler.
 *
 * @author Anatolii Hamza
 */
@ControllerAdvice(basePackages = "com.plant.api.controller")
public class ApplicationExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationExceptionHandler.class);
    private static final String EXCEPTION_MESSAGE = "An exception occurred: ";
    private static final String EXCEPTION_STRING_MESSAGE = "An exception occurred: {}";

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ServiceResponseBody> handleJsonProcessingException(JsonProcessingException e) {
        LOG.error(EXCEPTION_MESSAGE, e);
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(new ServiceResponseBody(JSON_PROCESSING.getText(), JSON_PROCESSING.getCode()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ServiceResponseBody> handleMessageNotReadableException(HttpMessageNotReadableException e) {
        var message = e.getMessage();
        if (e.getCause() instanceof MismatchedInputException) {
            var exception = (MismatchedInputException) e.getCause();
            var fields = exception.getPath().stream()
                    .map(JsonMappingException.Reference::getFieldName)
                    .collect(Collectors.joining(","));
            message = exception.getOriginalMessage() + ", fields: " + fields;
        }
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ServiceResponseBody(message, MESSAGE_NOT_READABLE.getCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServiceResponseBody> processValidationErrors(MethodArgumentNotValidException e) {
        var fieldErrors = e.getBindingResult().getFieldErrors();
        var errorMessage = "";
        for (FieldError fieldError : fieldErrors) {
            errorMessage += "'" + fieldError.getField() + "' - " + fieldError.getDefaultMessage() + "\n";
        }
        var globalErrors = e.getBindingResult().getGlobalErrors();
        for (ObjectError objectError : globalErrors) {
            errorMessage += "'" + objectError.getObjectName() + "' - " + objectError.getDefaultMessage() + "\n";
        }
        errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
        LOG.error(EXCEPTION_STRING_MESSAGE, errorMessage);
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ServiceResponseBody(errorMessage, METHOD_ARGUMENT_NOT_VALID.getCode()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ServiceResponseBody> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ServiceResponseBody(String.format("Expected %s type for parameter %s is invalid.",
                        e.getParameter().getParameterType().getName(),
                        e.getName()), INVALID_REQUEST_ARGUMENT.getCode()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ServiceResponseBody> handleConstraintViolationException(ConstraintViolationException e) {
        var errorMessage = e.getConstraintViolations().stream()
                .map(constraintViolation ->
                        constraintViolation.getPropertyPath().toString() + " " + constraintViolation.getMessage())
                .collect(Collectors.joining(", "));
        LOG.error(EXCEPTION_STRING_MESSAGE, errorMessage);
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ServiceResponseBody(CONSTRAINTS_VIOLATION.getText(), CONSTRAINTS_VIOLATION.getCode()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ServiceResponseBody> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e) {
        return ResponseEntity.status(BAD_REQUEST).body(
                new ServiceResponseBody(e.getMessage(), MESSAGE_NOT_READABLE.getCode()));
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ServiceResponseBody> handleRentalException(ServiceException e) {
        return ResponseEntity.status(e.getCode()).body(e.getBody());
    }
}
