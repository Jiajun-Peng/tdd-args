package com.jjpeng.tdd.args;

class IntegerParser extends StringParser {

    public IntegerParser() {
        super(Integer::parseInt);
    }
}
