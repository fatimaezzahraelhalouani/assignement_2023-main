package ma.octo.assignement.exceptions;

public class CompteExisteDejaException extends Exception{
    private static final long serialVersionUID = 1L;

    public CompteExisteDejaException() {
    }

    public CompteExisteDejaException(String message) {
        super(message);
    }
}
