package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.exception.TerminalNotFoundException;
import prr.app.exception.UnknownTerminalKeyException;

/**
 * Add a friend.
 */
class DoAddFriend extends TerminalCommand {
  Network _context;

  DoAddFriend(Network context, Terminal terminal) {
    super(Label.ADD_FRIEND, context, terminal);
    _context = context;
    addStringField("terminal", Message.terminalKey());
    addStringField("friend", Message.terminalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String terminal = stringField("terminal");
    String friend = stringField("friend");

    try {_context.addFriend(friend, terminal);}    
    catch (TerminalNotFoundException e) {       
      throw new UnknownTerminalKeyException(e.getTerminal());   //the teach made this one and didnt make a constructur that takes exceptions, so fuck it
    }
  }
}
