package com.jjpeng.tdd.args;

class IntegerParser extends StringParser {

    private IntegerParser() {
        super(Integer::parseInt);
    }

    public static IntegerParser createIntegerParser() {
        return new IntegerParser();
    }
}
