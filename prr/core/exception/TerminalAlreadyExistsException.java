package prr.core.exception;

public class TerminalAlreadyExistsException extends Exception {
    private String _id;
    public TerminalAlreadyExistsException(String id) {
        _id = id;
    }
    public TerminalAlreadyExistsException(String id, Exception cause) {
        super(cause);
        _id = id;
    }
    public String getTerminal() {
        return _id;
    }
}
