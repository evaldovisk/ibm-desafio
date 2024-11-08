package io.github.evaldo.ibm.exceptions.controllerException;

import io.github.evaldo.ibm.dto.DefaultHttpMessageResponse;
import io.github.evaldo.ibm.exceptions.BadRequestException;
import io.github.evaldo.ibm.exceptions.ObjectExistingException;
import io.github.evaldo.ibm.exceptions.ObjectNotFoundException;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<DefaultHttpMessageResponse<String>> objectNotFoundException(
            ObjectNotFoundException objectNotFoundException){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                DefaultHttpMessageResponse.<String>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(objectNotFoundException.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(ObjectExistingException.class)
    public ResponseEntity<DefaultHttpMessageResponse<String>> objectExistingException(
            ObjectExistingException objectExistingException){

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                DefaultHttpMessageResponse.<String>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.CONFLICT.value())
                        .message(objectExistingException.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<DefaultHttpMessageResponse<String>> badRequestException(
            BadRequestException badRequestException){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                DefaultHttpMessageResponse.<String>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(badRequestException.getMessage())
                        .build()
        );
    }

}
