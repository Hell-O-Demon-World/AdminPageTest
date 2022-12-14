package com.golfzonaca.adminpage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.place")
public class MisMatchingPasswordException extends NoSuchElementException {
    public MisMatchingPasswordException() {
    }

    public MisMatchingPasswordException(String msg) {
        super(msg);
    }
}
