package com.jjpeng.tdd.args;

class IntegerParser extends StringParser {

    @Override
    protected Object parseValue(String value) {
        return Integer.parseInt(value);
    }
}
