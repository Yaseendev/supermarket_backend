package com.yaseenworks.supermarket.advice;

import com.yaseenworks.supermarket.exceptions.UserExistsException;
import com.yaseenworks.supermarket.exceptions.UserNotFoundException;
import com.yaseenworks.supermarket.exceptions.WrongCredentialsException;
import io.jsonwebtoken.security.WeakKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleMessageNotReadableException(HttpMessageNotReadableException exception) {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("error", exception.getLocalizedMessage().split(":")[0]);
        return new ResponseEntity<Object>(errMap, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> handleMediaTypeException(HttpMediaTypeNotSupportedException exception) {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("error", exception.getLocalizedMessage());
        return new ResponseEntity<Object>(errMap, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errMap = new HashMap<>();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(error -> errMap.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<Object>(errMap, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Access Denied", HttpStatus.FORBIDDEN);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("error", exception.getMessage());
        return new ResponseEntity<Object>(errMap, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(WeakKeyException.class)
    public ResponseEntity<Object> handleWeakKeyException(WeakKeyException exception) {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("error", exception.getLocalizedMessage());
        return new ResponseEntity<Object>(errMap, HttpStatus.BAD_GATEWAY);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongCredentialsException.class)
    public ResponseEntity<Object> handleWrongCredentialsException(WrongCredentialsException exception) {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("error", exception.getLocalizedMessage());
        return new ResponseEntity<Object>(errMap, HttpStatus.BAD_GATEWAY);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleSQLException(SQLIntegrityConstraintViolationException exception) {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("error", exception.getLocalizedMessage());
        return new ResponseEntity<Object>(errMap, HttpStatus.BAD_GATEWAY);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Object> handleUserExistException(UserExistsException exception) {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("error", exception.getLocalizedMessage());
        return new ResponseEntity<Object>(errMap, HttpStatus.NOT_ACCEPTABLE);
    }
}
