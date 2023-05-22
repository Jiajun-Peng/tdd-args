package com.jjpeng.tdd.args;

public class TooManyArgumentsException extends RuntimeException {
    public TooManyArgumentsException() {
        super("Too many arguments");
    }
}
