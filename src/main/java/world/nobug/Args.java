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
        Class<?> type = parameter.getType();
        return PARSERS.get(type).parse(arguments, parameter.getAnnotation(Option.class));
    }

    private static Map<Class<?>, OptionParser> PARSERS = Map.of(
            boolean.class, new BooleanOptionParser(),
            int.class, new IntOptionParser(),
            String.class, new StringOptionParser());

    interface OptionParser {
        Object parse(List<String> arguments, Option option);
    }

    // 将分支中不同的地方抽取出来，使其与相同的地方隔离开

    static class BooleanOptionParser implements OptionParser {
        @Override
        public Object parse(List<String> arguments, Option option) {
            return arguments.contains("-" + option.value());
        }
    }

    static class IntOptionParser implements OptionParser {
        @Override
        public Object parse(List<String> arguments, Option option) {
            int index = arguments.indexOf("-" + option.value());
            return Integer.parseInt(arguments.get(index + 1));
        }
    }

    static class StringOptionParser implements OptionParser {
        @Override
        public Object parse(List<String> arguments, Option option) {
            int index = arguments.indexOf("-" + option.value());
            return arguments.get(index + 1);
        }
    }
}
