package world.nobug;

public class IllegalOptionException extends RuntimeException {
    private final String parameter;

    public IllegalOptionException(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return this.parameter;
    }
}
