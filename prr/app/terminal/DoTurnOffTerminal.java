package prr.app.terminal;

import prr.core.Network;
import prr.core.Parser;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Turn off the terminal.
 */
class DoTurnOffTerminal extends TerminalCommand {

  DoTurnOffTerminal(Network context, Parser.Terminal terminal) {
    super(Label.POWER_OFF, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
