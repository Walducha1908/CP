package cp.exceptions;

public class PersonNotFoundException extends Exception {
    public PersonNotFoundException(String message) {
        super(message);
    }

    public PersonNotFoundException() {
        super("Person not found");
    }
}
