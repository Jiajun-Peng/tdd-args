package com.jjpeng.tdd.args;

class IntegerParser extends StringParser {

    private IntegerParser() {
        super(Integer::parseInt);
    }

}
