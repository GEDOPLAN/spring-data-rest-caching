package de.gedoplan.demo.cachingrest.controller.config;

import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RepositoryConstraintViolationException.class)
    public final ResponseEntity<Object> handleCustomException(Exception ex, WebRequest request) {
        var violation = (RepositoryConstraintViolationException) ex;
        return ResponseEntity.badRequest()
                .body(violation.getErrors().getAllErrors()
                        .stream()
                        .map(ObjectError::getCode)
                        .collect(Collectors.joining(",")));
    }
}
