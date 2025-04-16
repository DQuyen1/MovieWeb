package com.example.movie.exception;


import com.example.movie.entity.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {




  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorMessage> handlerResourceNotFound(ResourceNotFoundException exc) {

  ErrorMessage err = new ErrorMessage();

  err.setStatus(HttpStatus.NOT_FOUND.value());
  err.setMsg(exc.getMessage());
  err.setTimestamp(new Date());

   return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorMessage> handlerInvalidArgument(MethodArgumentNotValidException exc) {

      Map<String, String> err = new HashMap<>();

        exc.getBindingResult().getFieldErrors().forEach(error -> {
          err.put(error.getField(), error.getDefaultMessage());
        });

      return new ResponseEntity<>(new ErrorMessage(400, err, new Date()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorMessage> handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exc) {
    ErrorMessage err = new ErrorMessage();

    err.setMsg(exc.getMessage());
    err.setStatus(HttpStatus.BAD_REQUEST.value());
    err.setTimestamp(new Date());


    return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ErrorMessage> handlerRestUrlNotFoundException(HttpServletRequest request) {

    ErrorMessage err = new ErrorMessage();
    err.setMsg("API named: " + request.getRequestURL() + " with method: " + request.getMethod() + " does not exist");
    err.setStatus(404);
    err.setTimestamp(new Date());

    return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorMessage> handlerNoRequestBody(HttpMessageNotReadableException exc) {

    ErrorMessage err = new ErrorMessage();
    err.setMsg(exc.getMessage());
    err.setStatus(400);
    err.setTimestamp(new Date());

    return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
  }


}

