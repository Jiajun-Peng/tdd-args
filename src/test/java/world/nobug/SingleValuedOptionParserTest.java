package world.nobug;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static world.nobug.BooleanOptionParserTest.option;
import java.util.List;
import java.util.function.Function;
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
    @Test // default value
    public void should_set_default_value_for_single_valued_option() {
        Function<String, Object> whateverValueParser = (it) -> null; // 因为并不关心valueParser的具体实现，所以这里随便写一个
        Object defaultValue = new Object();
        // 只需要保证原样输出，也不关心option的具体值
        assertEquals(defaultValue, new SingleValuedOptionParser<>(whateverValueParser, defaultValue)
                .parse(List.of(), option("p")));
        assertEquals(defaultValue, new SingleValuedOptionParser<>(whateverValueParser, defaultValue)
                .parse(List.of(), option("d")));
    }

    @Test // happy path
    public void should_parse_value_if_flag_present() {
        Object parsed = new Object();
        Function<String, Object> parser = (it) -> parsed;
        Object whateverDefaultValue = new Object();

        // 并不关心执行的parser是什么方法，只需要保证执行后的值是跟预期的一样就行，无论这个是String::valueOf还是Integer::parseInt
        assertEquals(parsed, new SingleValuedOptionParser<>(parser, whateverDefaultValue)
                .parse(List.of("-p", "8080"), option("p")));
        assertEquals(parsed, new SingleValuedOptionParser<>(parser, whateverDefaultValue)
                .parse(List.of("-d", "/usr/logs"), option("d")));
    }
}
