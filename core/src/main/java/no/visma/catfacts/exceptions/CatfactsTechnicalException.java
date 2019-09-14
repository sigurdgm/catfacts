package no.visma.catfacts.exceptions;

public class CatfactsTechnicalException extends RuntimeException {

    public CatfactsTechnicalException(String message) {
        super(message);
    }

    public CatfactsTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
