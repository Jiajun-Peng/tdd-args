package com.jjpeng.tdd.args;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BooleanOptionParserTest {

    // Sad path:
    // -l t
    @Test
    public void should_not_accept_extra_argument() {
        assertThrows(TooManyArgumentsException.class, () -> {
            new BooleanOptionParser().parse(Arrays.asList("-l", "t"), option("l"));
        });
    }

    // -l t f
    @Test
    public void should_not_accept_extra_arguments() {
        assertThrows(TooManyArgumentsException.class, () -> {
            new BooleanOptionParser().parse(Arrays.asList("-l", "t", "f"), option("l"));
        });
    }

    // default value:
    @Test
    public void should_set_default_value_if_not_present() {
        assertFalse(new BooleanOptionParser().parse(Arrays.asList(), option("l")));
    }


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
