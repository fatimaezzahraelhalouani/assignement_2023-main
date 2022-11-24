package ma.octo.assignement.exceptions;

public class AuditNonValideException extends Exception{
    private static final long serialVersionUID = 1L;

    public AuditNonValideException() {
    }

    public AuditNonValideException(String message) {
        super(message);
    }
}
