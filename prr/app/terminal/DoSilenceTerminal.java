package prr.app.terminal;

import prr.core.Network;
import prr.core.Parser;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Silence the terminal.
 */
class DoSilenceTerminal extends TerminalCommand {

  DoSilenceTerminal(Network context, Parser.Terminal terminal) {
    super(Label.MUTE_TERMINAL, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
