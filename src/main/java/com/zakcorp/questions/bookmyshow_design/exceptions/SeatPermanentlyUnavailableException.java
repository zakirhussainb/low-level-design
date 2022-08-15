package com.zakcorp.questions.bookmyshow_design.exceptions;

public class SeatPermanentlyUnavailableException extends RuntimeException {
    public SeatPermanentlyUnavailableException(String message) {
        super(message);
    }
}
