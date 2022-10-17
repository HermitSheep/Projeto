package prr.app.terminal;

import prr.core.Network;
import prr.core.Parser;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Turn on the terminal.
 */
class DoTurnOnTerminal extends TerminalCommand {

  DoTurnOnTerminal(Network context, Parser.Terminal terminal) {
    super(Label.POWER_ON, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
