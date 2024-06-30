package world.nobug;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

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
        Option option = parameter.getAnnotation(Option.class);
        Object value = null;
        if (parameter.getType() == boolean.class) {
            value = parseBoolean(arguments, option);
        }
        if (parameter.getType() == int.class) {
            value = parseInt(arguments, option);
        }
        if (parameter.getType() == String.class) {
            value = parseString(arguments, option);
        }
        return value;
    }

    private static Object parseString(List<String> arguments, Option option) {
        Object value;
        int index = arguments.indexOf("-" + option.value());
        value = arguments.get(index + 1);
        return value;
    }

    private static Object parseInt(List<String> arguments, Option option) {
        Object value;
        int index = arguments.indexOf("-" + option.value());
        value = Integer.parseInt(arguments.get(index + 1));
        return value;
    }

    // 将分支中不同的地方抽取出来，使其与相同的地方隔离开
    private static boolean parseBoolean(List<String> arguments, Option option) {
        return arguments.contains("-" + option.value());
    }
}
