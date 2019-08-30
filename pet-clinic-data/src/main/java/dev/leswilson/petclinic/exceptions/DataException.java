package dev.leswilson.petclinic.exceptions;

public class DataException extends RuntimeException {
    public DataException() {
        super();
    }

    public DataException(String message) {
        super(message);
    }
}
