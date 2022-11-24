package ma.octo.assignement.exceptions;

public class UtilisateurExisteDejaException extends Exception{
    private static final long serialVersionUID = 1L;

    public UtilisateurExisteDejaException() {
    }

    public UtilisateurExisteDejaException(String message) {
        super(message);
    }
}
