package prr.app.terminal;

import prr.core.Network;
import prr.core.Parser;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for showing the ongoing communication.
 */
class DoShowOngoingCommunication extends TerminalCommand {

  DoShowOngoingCommunication(Network context, Parser.Terminal terminal) {
    super(Label.SHOW_ONGOING_COMMUNICATION, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
