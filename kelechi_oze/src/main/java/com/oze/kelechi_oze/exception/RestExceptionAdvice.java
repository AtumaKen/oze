package com.oze.kelechi_oze.exception;

import com.oze.kelechi_oze.models.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class RestExceptionAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return Response.builder().data(errors).code("400").message("Validation error").build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Response handleNotfoundExceptions(
            NotFoundException ex) {


        return Response.builder().code(ex.getCode()).message(ex.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public Response handleBadRequestExceptions(
            BadRequestException ex) {


        return Response.builder().code(ex.getCode()).message(ex.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CSVException.class)
    public Response handleCSVException(
            CSVException ex) {


        return Response.builder().code(ex.getCode()).message(ex.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response handleGeneralExceptions(
            Exception ex) {

        log.error(ex.getMessage());
        return Response.builder().code("99").message(ex.getMessage()).build();
    }

    //MissingServletRequestParameterException

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Response handleRequestParameterExceptions(
            MissingServletRequestParameterException ex) {

        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getParameterName(), ex.getParameterType());
        return Response.builder().code("400")
                .message(ex.getMessage()).data(errors).build();
    }
}
