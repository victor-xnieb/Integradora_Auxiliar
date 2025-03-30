package exceptions;

public class PassengerAlreadyExistsException extends RuntimeException {

    public PassengerAlreadyExistsException(String message) {
        super(message);
    }
}
