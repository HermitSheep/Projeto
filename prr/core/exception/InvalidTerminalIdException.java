package prr.core.exception;

public class InvalidTerminalIdException extends Exception{
    private String _id;
    public InvalidTerminalIdException(String id) {
        _id = id;
    }
    public InvalidTerminalIdException(String id, Exception cause) {
        super(cause);
        _id = id;
    }
    public String getTerminal() {
        return _id;
    }
}
