package ma.octo.assignement.exceptions;

public class CompteNonValidException extends Exception{
    private static final long serialVersionUID = 1L;

    public CompteNonValidException() {
    }

    public CompteNonValidException(String message) {
        super(message);
    }
}
