package io.codechobostudy.user.controller;

import io.codechobostudy.user.exception.BadRequestException;
import io.codechobostudy.user.exception.ConflictException;
import io.codechobostudy.user.exception.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice("io.codechobostudy.user")
public class ControllerExceptionAdvice {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void badRequestException(BadRequestException e) {
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void badRequestException(ConflictException e) {
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void badRequestException(InternalServerErrorException e) {
    }

}
