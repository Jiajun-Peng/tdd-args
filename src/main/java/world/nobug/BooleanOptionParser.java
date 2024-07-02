package world.nobug;

import java.util.List;

class BooleanOptionParser implements OptionParser {
    @Override
    public Object parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        return index != -1;
    }
}
