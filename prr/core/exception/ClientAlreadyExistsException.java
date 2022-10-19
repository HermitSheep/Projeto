package prr.core.exception;

public class ClientAlreadyExistsException extends Exception {
    private String _key;
    public ClientAlreadyExistsException(String ClientKey) {
        _key = ClientKey;
    }
    public ClientAlreadyExistsException(String ClientKey, Exception cause) {
        super(cause);
        _key = ClientKey;
    }
    public String getClientKey() {
        return _key;
    }
}
