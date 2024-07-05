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
    //  - int -p/ -p 8080 8081
    @Test// sad path
    public void should_not_accept_extra_argument_for_single_valued_option() {
        TooManyArgumentsException p = assertThrows(TooManyArgumentsException.class,
                () -> new SingleValuedOptionParser<>(Integer::parseInt, 0)
                        .parse(List.of("-p", "8080", "8081"),
                        option("p")));

        assertEquals("p", p.getOption());
    }

    @ParameterizedTest // sad path
    @ValueSource(strings = { "-p -l", "-p"})
    public void should_not_accept_insufficient_argument_for_single_valued_option(String args) {
        InsufficientArgumentsException e = assertThrows(InsufficientArgumentsException.class,
                () -> new SingleValuedOptionParser<>(Integer::parseInt, 0).parse(List.of(args.split(" ")),
                        option("p")));

        assertEquals("p", e.getOption());
    }

    //  - string -d/ -d /usr/logs /usr/vars
    @Test
    public void should_not_accept_extra_argument_for_string_single_valued_option() {
        TooManyArgumentsException p = assertThrows(TooManyArgumentsException.class,
                () -> new SingleValuedOptionParser<>(Integer::parseInt, 0)
                        .parse(List.of("-d", "/usr/logs", "/usr/vars"),
                                option("d")));

        assertEquals("d", p.getOption());
    }

    @ParameterizedTest
    @ValueSource(strings = { "-d -l", "-d"})
    public void should_not_accept_insufficient_argument_for_string_single_valued_option(String args) {
        InsufficientArgumentsException e = assertThrows(InsufficientArgumentsException.class,
                () -> new SingleValuedOptionParser<>(Integer::parseInt, 0).parse(List.of(args.split(" ")),
                        option("d")));

        assertEquals("d", e.getOption());
    }


    // default value:
    //  -int :0
    @Test // default value
    public void should_set_default_value_to_0_for_int_option() {
        assertEquals(0, new SingleValuedOptionParser<>(Integer::parseInt, 0)
                .parse(List.of(), option("p")));
    }

    // -int -p 8080
    @Test // happy path -int -p 8080
    public void should_parser_value_if_flag_present() {
        assertEquals(8080, new SingleValuedOptionParser<>(Integer::parseInt, 0)
                .parse(List.of("-p", "8080"), option("p")));
    }

    // default value:
    //  - string ""
    @Test
    public void should_set_default_value_to_blank_for_int_option() {
        // 结合生产代码发现，对于默认值的处理，这里已经不需要使用到valueParser，而且这里引入的valueParser还是错误的，
        // 正确的String::valueOf，而不是Integer::parseInt
        assertEquals("", new SingleValuedOptionParser<>(Integer::parseInt, "")
                .parse(List.of(), option("d")));

    }
}
