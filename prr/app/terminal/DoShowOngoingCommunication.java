package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.exception.NoOngoigComException;

/**
 * Command for showing the ongoing communication.
 */
class DoShowOngoingCommunication extends TerminalCommand {

  DoShowOngoingCommunication(Network context, Terminal terminal) {
    super(Label.SHOW_ONGOING_COMMUNICATION, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      _display.addLine(_receiver.showOngoingCom());
    }
    catch (NoOngoigComException e) {
      _display.addLine(Message.noOngoingCommunication());   //idk if i should do it like this or throw an exception
    }
    _display.display();
  }
}
