package world.nobug;

import java.util.List;
import java.util.function.Function;

class SingleValuedOptionParser<T> implements OptionParser<T> {
    Function<String, T> valueParser;
    T defaultValue;

    private SingleValuedOptionParser(Function<String, T> valueParser) {
        this.valueParser = valueParser;
    }

    public SingleValuedOptionParser(Function<String, T> valueParser, T defaultValue) {
        this.valueParser = valueParser;
        this.defaultValue = defaultValue;
    }

    public static <T> SingleValuedOptionParser<T> createSingleValuedOptionParser(Function<String, T> valueParser) {
        return new SingleValuedOptionParser<T>(valueParser);
    }

    @Override
    public T parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        if (index == -1) return defaultValue;
        if (arguments.size() == index + 1 ||
                arguments.get(index + 1).startsWith("-")) throw new InsufficientArgumentsException(option.value());
        if (arguments.size() > index + 2 &&
                !arguments.get(index + 2).startsWith("-")) throw new TooManyArgumentsException(option.value());
        return valueParser.apply(arguments.get(index + 1));
    }

}
