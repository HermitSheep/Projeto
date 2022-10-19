package prr.core.exception;

public class TerminalNotFoundException extends Exception {
    private String _id;
    public TerminalNotFoundException(String id) {
        _id = id;
    }
    public TerminalNotFoundException(String id, Exception cause) {
        super(cause);
        _id = id;
    }
    public String getTerminal() {
        return _id;
    }
}
