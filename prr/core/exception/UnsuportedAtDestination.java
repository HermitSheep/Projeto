package prr.core.exception;

public class UnsuportedAtDestination extends Exception{
    String _term;
    public UnsuportedAtDestination(String term) {
        _term = term;
    }
}
