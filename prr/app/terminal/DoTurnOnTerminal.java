package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.exception.TerminalNotFoundException;
import prr.core.exception.StateNotChangedException;
import prr.app.exception.UnknownTerminalKeyException;

/**
 * Turn on the terminal.
 */
class DoTurnOnTerminal extends TerminalCommand {
  Network _context;

  DoTurnOnTerminal(Network context, Terminal terminal) {
    super(Label.POWER_ON, context, terminal);
    _context = context;
    addStringField("terminal", Message.terminalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String terminal = stringField("terminal");
    try {_context.turnIdleTerminal(terminal);}                //here i assume it's supposed to become iddle instead of on
    catch (TerminalNotFoundException e) {       
      throw new UnknownTerminalKeyException(e.getTerminal());   
    }
    catch(StateNotChangedException e){
      System.out.println(Message.alreadyOn());
    }
  }
}
