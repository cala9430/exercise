package com.example.trains.excercise.exceptions;

public class StationNotFound extends RuntimeException {
    public StationNotFound(String message) {
        super(String.format("Station not found: %s", message));
    }
}
