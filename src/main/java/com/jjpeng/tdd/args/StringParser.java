package com.jjpeng.tdd.args;

import java.util.List;
import java.util.function.Function;

class StringParser implements OptionParser {

    private Function<String, Object> valueParser = String::valueOf;

    @Override
    public Object parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        String value = arguments.get(index + 1);
        return parseValue(value);
    }

    protected Object parseValue(String value) {
        return valueParser.apply(value);
    }
}
