package dev.leswilson.petclinic.exceptions;

public class DataException extends RuntimeException {
    public DataException(String message) {
        super(message);
    }
}
