package world.nobug;

import java.util.List;
import java.util.function.Function;

class SingleValuedOptionParser<T> implements OptionParser<T> {
    Function<String, T> valueParser;

    public SingleValuedOptionParser(Function<String, T> valueParser) {
        this.valueParser = valueParser;
    }

    @Override
    public T parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        if (!arguments.get(index + 2).startsWith("-")) throw new TooManyArgumentsException(option.value());
        return valueParser.apply(arguments.get(index + 1));
    }

}
