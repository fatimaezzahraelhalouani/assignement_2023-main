package ma.octo.assignement.exceptions;

public class TransferNonExistantException extends Exception{

    private static final long serialVersionUID = 1L;

    public TransferNonExistantException() {
    }

    public TransferNonExistantException(String message) {
        super(message);
    }
}
