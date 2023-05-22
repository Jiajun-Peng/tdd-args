package com.jjpeng.tdd.args;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BooleanOptionParserTest {

    // Sad path:
    // TODO: - Bool -l t / -l t f
    @Test
    public void should_not_accept_extra_argument() {
        assertThrows(TooManyArgumentsException.class, () -> {
            new BooleanOptionParser().parse(Arrays.asList("-l", "t"), option("l"));
        });
    }

    // default value:
    // TODO: - Bool -l:false


    static Option option(String value) {
        return new Option() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return Option.class;
            }

            @Override
            public String value() {
                return value;
            }
        };
    }
}
