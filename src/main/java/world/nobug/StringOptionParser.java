package world.nobug;

class StringOptionParser extends IntOptionParser {

    public static OptionParser createStringOptionParser() {
        return new IntOptionParser(String::valueOf);
    }
}
