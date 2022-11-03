package prr.core.exception;

public class UnsuportedAtOrigin  extends Exception{
    String _term;
    public UnsuportedAtOrigin(String term) {
        _term = term;
    }
}
