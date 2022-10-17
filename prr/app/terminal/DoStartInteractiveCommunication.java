package prr.app.terminal;

import prr.core.Network;
import prr.core.Parser;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for starting communication.
 */
class DoStartInteractiveCommunication extends TerminalCommand {

  DoStartInteractiveCommunication(Network context, Parser.Terminal terminal) {
    super(Label.START_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
