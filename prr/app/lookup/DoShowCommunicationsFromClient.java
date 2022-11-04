package prr.app.lookup;

import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;
import prr.core.exception.ClientNotFoundException;
import prr.app.exception.UnknownClientKeyException;

/**
 * Show communications from a client.
 */
class DoShowCommunicationsFromClient extends Command<Network> {

  DoShowCommunicationsFromClient(Network receiver) {
    super(Label.SHOW_COMMUNICATIONS_FROM_CLIENT, receiver);
    addStringField("client", Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException {
    String client = stringField("client");
    try {
    List<String> lines = _receiver.comsFromClient(client);
    for (String line : lines)
      _display.addLine(line);
    _display.display();
    }
    catch (ClientNotFoundException e) {
      throw new UnknownClientKeyException(client);
    }
  }
}
