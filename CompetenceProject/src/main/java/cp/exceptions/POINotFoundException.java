package cp.exceptions;

public class POINotFoundException extends Exception {
    public POINotFoundException(String message) {
        super(message);
    }

    public POINotFoundException() {
        super("Point Of Interest not found");
    }
}
