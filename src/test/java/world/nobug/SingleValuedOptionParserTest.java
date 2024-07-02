package world.nobug;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static world.nobug.BooleanOptionParserTest.option;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SingleValuedOptionParserTest {

    // sad path:
    // TODO: - int -p/ -p 8080 8081
    @Test
    public void should_not_accept_extra_argument_for_single_valued_option() {
        TooManyArgumentsException p = assertThrows(TooManyArgumentsException.class,
                () -> SingleValuedOptionParser.createSingleValuedOptionParser(Integer::parseInt, 0)
                        .parse(List.of("-p", "8080", "8081"),
                        option("p")));

        assertEquals("p", p.getOption());
    }

    @ParameterizedTest
    @ValueSource(strings = { "-p -l", "-p"})
    public void should_not_accept_insufficient_argument_for_single_valued_option(String args) {
        InsufficientArgumentsException e = assertThrows(InsufficientArgumentsException.class,
                () -> SingleValuedOptionParser.createSingleValuedOptionParser(Integer::parseInt, 0).parse(List.of(args.split(" ")),
                        option("p")));

        assertEquals("p", e.getOption());
    }

    // TODO: - string -d/ -d /usr/logs /usr/vars


    // default value:
    // TODO: -int :0
    @Test
    public void should_set_default_value_to_0_for_int_option() {
        assertEquals(0, SingleValuedOptionParser.createSingleValuedOptionParser(Integer::parseInt, 0)
                .parse(List.of(), option("p")));
    }
    // TODO: - string ""
}
