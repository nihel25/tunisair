package tn.esprit.tunisair.Service;

public class ReferenceUniqueException extends RuntimeException {

    public ReferenceUniqueException() {
        super();
    }

    public ReferenceUniqueException(String message) {
        super(message);
    }

    public ReferenceUniqueException(String message, Throwable cause) {
        super(message, cause);
    }
}
