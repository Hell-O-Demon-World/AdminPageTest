package com.golfzonaca.adminpage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.inquiry")
public class NonExistedInquiryException extends NoSuchElementException {

    public NonExistedInquiryException(String msg) {
        super(msg);
    }
}
