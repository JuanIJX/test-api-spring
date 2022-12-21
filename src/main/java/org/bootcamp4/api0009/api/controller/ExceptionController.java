package org.bootcamp4.api0009.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import org.bootcamp4.api0009.libraries.specifical.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<HashMap<String, Object>> argumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        return Response.get(request, response -> {
            response.setErrorMsg(ex.getMessage());
            response.setErrorCode(102);
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            return null;
        }, getClass(), "argumentTypeMismatchException");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HashMap<String, Object>> genericException(Exception ex, HttpServletRequest request) {
        return Response.get(request, response -> {
            response.setErrorMsg(ex.getMessage());
            response.setErrorCode(100);
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            return null;
        }, getClass(), "genericException");
    }
}
