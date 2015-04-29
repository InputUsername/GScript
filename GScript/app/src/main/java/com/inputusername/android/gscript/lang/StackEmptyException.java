package com.inputusername.android.gscript.lang;

public class StackEmptyException extends RuntimeException {

    public StackEmptyException(String message) {
        super(message);
    }

    public StackEmptyException() {
        super();
    }

}
