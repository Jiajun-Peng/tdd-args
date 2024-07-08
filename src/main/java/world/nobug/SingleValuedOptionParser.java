package world.nobug;

import java.util.List;
import java.util.function.Function;

class SingleValuedOptionParser<T> implements OptionParser<T> {
    Function<String, T> valueParser;
    T defaultValue;

    public SingleValuedOptionParser(Function<String, T> valueParser, T defaultValue) {
        this.valueParser = valueParser;
        this.defaultValue = defaultValue;
    }

    @Override
    public T parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        if (index == -1) return defaultValue;
        if (isReachEndOfList(arguments, index) ||
                isFollowedByOtherFlag(arguments, index)) throw new InsufficientArgumentsException(option.value());
        if (secondArgumentIsNotFlag(arguments, index)) throw new TooManyArgumentsException(option.value());
        return valueParser.apply(arguments.get(index + 1));
    }

    private static boolean secondArgumentIsNotFlag(List<String> arguments, int index) {
        return arguments.size() > index + 2 &&
                !arguments.get(index + 2).startsWith("-");
    }

    private static boolean isFollowedByOtherFlag(List<String> arguments, int index) {
        return arguments.get(index + 1).startsWith("-");
    }

    private static boolean isReachEndOfList(List<String> arguments, int index) {
        return arguments.size() == index + 1;
    }

}
