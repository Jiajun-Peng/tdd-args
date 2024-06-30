package world.nobug;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Args {
    public static <T> T parse(Class<T> optionsClass, String... args) {
        try {
            Constructor<?> constructor = optionsClass.getDeclaredConstructors()[0];
            List<String> arguments = Arrays.asList(args);

            Object[] values = Arrays.stream(constructor.getParameters()).map(it -> parseOption(arguments, it)).toArray();

            return (T) constructor.newInstance(values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 预期利用多态替换条件分支
    private static Object parseOption(List<String> arguments, Parameter parameter) {
        return PARSERS.get(parameter.getType()).parse(arguments, parameter.getAnnotation(Option.class));
    }

    private static Map<Class<?>, OptionParser> PARSERS = Map.of(
            boolean.class, new BooleanOptionParser(),
            int.class, new IntOptionParser(),
            String.class, new StringOptionParser());

}
