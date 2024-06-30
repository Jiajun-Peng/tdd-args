package world.nobug;

class StringOptionParser extends IntOptionParser {

    public StringOptionParser() {
        super(String::valueOf);
    }
}
