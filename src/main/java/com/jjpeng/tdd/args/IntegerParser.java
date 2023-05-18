package com.jjpeng.tdd.args;

class IntegerParser extends StringParser {

    private IntegerParser() {
        super(Integer::parseInt);
    }

    public static OptionParser createIntegerParser() {
        return new StringParser(Integer::parseInt);
    }
}
