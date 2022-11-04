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

  DoTurnOnTerminal(Network context, Terminal terminal) {
    super(Label.POWER_ON, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {_network.turnIdleTerminal(_receiver.getId());}
    catch(StateNotChangedException e){
      _display.addLine(Message.alreadyOn());
      _display.display();
    }
  }
}
