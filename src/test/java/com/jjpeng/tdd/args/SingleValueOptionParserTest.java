package com.jjpeng.tdd.args;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.jjpeng.tdd.args.BooleanOptionParserTest.option;

public class SingleValueOptionParserTest {
    // Sad path:
    // TODO: - Integer -p 8080 8081
    @Test
    public void should_not_accept_extra_argument() {
        Assertions.assertThrows(TooManyArgumentsException.class, () -> {
            new SingleValueOptionParser<>(Integer::parseInt).parse(Arrays.asList("-p", "8080", "8081"), option("p"));
        });
    }

    // TODO: - String -d / -d /usr/logs /usr/vars

    // default value:
    // TODO: - Integer -p:0
    // TODO: - String -d:""
}
