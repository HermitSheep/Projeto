package prr.core.exception;

import prr.core.TerminalMode;

public class UnavailableTerminalException extends Exception{
  String _term;
  String _mode;
  public UnavailableTerminalException(String term, TerminalMode mode) {
    _term = term;
    _mode = mode.toString();
  }

  public String getTerm() {
    return _term;
  }

  public String getMode() {
    return _mode;
  }
}
