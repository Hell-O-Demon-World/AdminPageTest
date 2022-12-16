package com.golfzonaca.adminpage.exhandler.advice;

import com.golfzonaca.adminpage.exception.NonExistedInquiryException;
import com.golfzonaca.adminpage.exception.WrongAddressException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongAddressException.class)
    public String illegalExHandler(WrongAddressException e, Model model) {
        log.error("[exceptionHandle] ex", e);
        model.addAttribute("message", e.getMessage());
        return "error/4xx";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NonExistedInquiryException.class)
    public String illegalExHandler(NonExistedInquiryException e, Model model) {
        log.error("[exceptionHandle] ex", e);
        model.addAttribute("message", e.getMessage());
        return "error/4xx";
    }
}
