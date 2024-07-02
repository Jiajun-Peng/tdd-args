package world.nobug;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.lang.annotation.Annotation;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BooleanOptionParserTest {

    // sad path:
    //  -bool -l t / -l t f
    @Test
    public void should_not_accept_extra_argument_for_boolean_option() {
        TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                () -> new BooleanOptionParser().parse(List.of("-l", "t"), option("l")));

        assertEquals("l", e.getOption());
    }

    @Test
    public void should_not_accept_extra_arguments_for_boolean_option() {
        TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                () -> new BooleanOptionParser().parse(List.of("-l", "t", "f"), option("l")));

        assertEquals("l", e.getOption());
    }

    /*
    这个option方法是一个辅助方法，用于创建一个Option注解实例。
    在Java中，注解是不能直接实例化的，但是在测试中，我们可能需要创建注解实例来模拟实际的使用场景。
    这个方法接收一个String类型的value，然后返回一个Option注解的匿名实现，其中value方法返回的就是传入的value。
     */
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

    // default:
    //  - bool : false
    @Test
    public void should_return_false_for_boolean_option_if_flag_not_present() {
        assertFalse(new BooleanOptionParser().parse(List.of(), option("l")));
    }
}
