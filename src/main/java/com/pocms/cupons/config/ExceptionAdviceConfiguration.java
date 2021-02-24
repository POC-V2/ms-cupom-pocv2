package com.pocms.cupons.config;

import com.pocms.cupons.config.data.Error;
import com.pocms.cupons.exception.CupomNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
@Hidden
public class ExceptionAdviceConfiguration {

    @ResponseBody
    @ExceptionHandler(CupomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error notFound(CupomNotFoundException e) {
        return new Error("XXX_100", e.getMessage(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error badRequest(Exception e) {
        return new Error("XXX_200", e.getCause().getMessage(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error internalServerError(Exception e) {
        return new Error("XXX_300", e.getMessage(), e.getMessage());
    }

}
