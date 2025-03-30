package exceptions;

public class IncidentAlreadyExistsException extends RuntimeException {

    public IncidentAlreadyExistsException(String message) {
        super(message);
    }
}
