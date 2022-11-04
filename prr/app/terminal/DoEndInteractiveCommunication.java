package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
//import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.exception.cantEndComException;
import prr.core.exception.NoOngoigComException;
import prr.core.exception.StateNotChangedException;

/**
 * Command for ending communication.
 */
class DoEndInteractiveCommunication extends TerminalCommand {

  DoEndInteractiveCommunication(Network context, Terminal terminal) {
    super(Label.END_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canEndCurrentCommunication());
    addIntegerField("dur", Message.duration());
  }
  
  @Override
  protected final void execute() throws CommandException {
    long cost;
    int dur = integerField("dur");
    try {
      cost = _network.endInteractiveCommunication(_receiver, dur);
      _display.addLine(Message.communicationCost(cost));
      _display.display();
    }
    catch (StateNotChangedException e) {
      //do something FIXME
    }
  }
}
