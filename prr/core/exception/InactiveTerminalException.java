package prr.core.exception;

import prr.core.TerminalMode;

public class InactiveTerminalException extends Exception{
  String _term;
  String _mode;
  public InactiveTerminalException(String term, TerminalMode mode) {
    _term = term;
    _mode = mode.toString();
  }

  public String getTerm() {
    return _term;
  }
}
