package prr.app.terminal;

import prr.app.terminal.Message;
import pt.tecnico.uilib.menus.CommandException;

public class DestinationTerminalOffException extends CommandException{
  /** @param key Unknown terminal to report. */
  public DestinationTerminalOffException(String key) {
    super(prr.app.terminal.Message.destinationIsOff(key));
  }
}
