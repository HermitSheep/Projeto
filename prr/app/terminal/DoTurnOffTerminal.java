package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.exception.TerminalNotFoundException;
import prr.core.exception.StateNotChangedException;
import prr.app.exception.UnknownTerminalKeyException;

/**
 * Turn off the terminal.
 */
class DoTurnOffTerminal extends TerminalCommand {
  Network _context;

  DoTurnOffTerminal(Network context, Terminal terminal) {
    super(Label.POWER_OFF, context, terminal);
    _context = context;
    addStringField("terminal", Message.terminalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String terminal = stringField("terminal");
    try {_context.turnOffTerminal(terminal);}    
    catch (TerminalNotFoundException e) {       
      throw new UnknownTerminalKeyException(e.getTerminal());   
    }
    catch(StateNotChangedException e){
      System.out.println(Message.alreadyOff());
    }
  }
  
}
