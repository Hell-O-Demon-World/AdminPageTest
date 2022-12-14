package com.golfzonaca.adminpage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.user")
public class NonexistentRestRoomForSelectedPlaceAndDateTime extends NoSuchElementException {
    public NonexistentRestRoomForSelectedPlaceAndDateTime() {
    }

    public NonexistentRestRoomForSelectedPlaceAndDateTime(String msg) {
        super(msg);
    }
}
