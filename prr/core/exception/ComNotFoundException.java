package prr.core.exception;

public class ComNotFoundException extends Exception{
    private int _id;
    public ComNotFoundException(int id) {
        _id = id;
    }
    public ComNotFoundException(int id, Exception cause) {
        super(cause);
        _id = id;
    }
    public int getCom() {
        return _id;
    }
}