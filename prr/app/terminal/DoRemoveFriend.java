package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.exception.TerminalNotFoundException;
import prr.app.exception.UnknownTerminalKeyException;

/**
 * Remove friend.
 */
class DoRemoveFriend extends TerminalCommand {
  Network _context;

  DoRemoveFriend(Network context, Terminal terminal) {
    super(Label.REMOVE_FRIEND, context, terminal);
    _context = context;
    addStringField("terminal", Message.terminalKey());
    addStringField("friend", Message.terminalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String terminal = stringField("terminal");
    String friend = stringField("friend");

    try {_context.removeFriend(friend, terminal);}    
    catch (TerminalNotFoundException e) {       
      throw new UnknownTerminalKeyException(e.getTerminal());   //teacher didnt include an exception in the constructor, so fuck it
    }
  }
}
