package no.visma.catfacts.exceptions;

public class CatfactsFunctionException extends RuntimeException {

    public CatfactsFunctionException(String message) {
        super(message);
    }

    public CatfactsFunctionException(String message, Throwable cause) {
        super(message, cause);
    }
}
