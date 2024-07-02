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
        if (index == -1) return 0; // 这里我们希望返回0，但是不符合方法的定义，所以这里是一个错误的实现
        if (arguments.size() == index + 1 ||
                arguments.get(index + 1).startsWith("-")) throw new InsufficientArgumentsException(option.value());
        if (arguments.size() > index + 2 &&
                !arguments.get(index + 2).startsWith("-")) throw new TooManyArgumentsException(option.value());
        return valueParser.apply(arguments.get(index + 1));
    }

}
