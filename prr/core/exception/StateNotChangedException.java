package prr.core.exception;
import prr.core.TerminalMode;

public class StateNotChangedException extends Exception{
    private String _term;
    private TerminalMode _state;
    private String _clientKey;
    private boolean _clientState;

    public StateNotChangedException(String key, boolean clientState){
        _clientKey = key;
        _clientState = clientState;
    }

    public String getClient() {
         return _clientKey;
    }

    public boolean getClientState() {
         return _clientState;
    }

    public StateNotChangedException(String term, TerminalMode state) {
        _term = term;
        _state = state;
    }

    public String getTerminal(){
        return _term;
    }

    public TerminalMode getState(){
        return _state;
    }
}