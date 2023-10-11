package ru.madrabit.frankenstein.http.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice(basePackages = "ru.madrabit.frankenstein.http.rest")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

}
