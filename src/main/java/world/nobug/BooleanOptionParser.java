package world.nobug;

import java.util.List;

class BooleanOptionParser implements OptionParser<Boolean> {
    @Override
    public Boolean parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        if (arguments.size() > index + 1 &&
                !arguments.get(index + 1).startsWith("-")) throw new TooManyArgumentsException(option.value());
        return index != -1;
    }
}
