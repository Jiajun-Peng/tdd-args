package com.jjpeng.tdd.args;

import java.util.List;

class IntegerParser implements OptionParser {

    @Override
    public Object parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        String value = arguments.get(index + 1);
        return Integer.parseInt(value);
    }
}
