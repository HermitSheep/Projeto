package prr.core.exception;

public class ClientNotFoundException extends Exception{
    private String _key;
    public ClientNotFoundException(String key) {
        _key = key;
    }
    public ClientNotFoundException(String key, Exception cause) {
        super(cause);
        _key = key;
    }
    public String getClient() {
        return _key;
    }
}
