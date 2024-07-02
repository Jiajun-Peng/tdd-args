package world.nobug;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static world.nobug.BooleanOptionParserTest.option;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SingleValuedOptionParserTest {

    // sad path:
    // TODO: - int -p/ -p 8080 8081
    @Test
    public void should_not_accept_extra_argument_for_single_valued_option() {
        TooManyArgumentsException p = assertThrows(TooManyArgumentsException.class,
                () -> new SingleValuedOptionParser<>(Integer::parseInt).parse(List.of("-p", "8080", "8081"),
                        option("p")));

        assertEquals("p", p.getOption());
    }

    // TODO: - string -d/ -d /usr/logs /usr/vars


    // default value:
    // TODO: -int :0
    // TODO: - string ""
}
