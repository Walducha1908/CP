package cp.exceptions;

public class TraceNotFoundException extends Throwable {
    public TraceNotFoundException() {
        super("Trace not found");
    }

    public TraceNotFoundException(String message) {
        super(message);
    }
}
