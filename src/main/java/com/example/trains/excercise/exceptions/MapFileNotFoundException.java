package com.example.trains.excercise.exceptions;

import java.io.FileNotFoundException;

public class MapFileNotFoundException extends FileNotFoundException {

    public MapFileNotFoundException(String s) {
        super(s);
    }
}
