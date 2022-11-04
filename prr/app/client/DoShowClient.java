package prr.app.client;

import prr.core.Network;

import java.util.List;

import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import prr.core.exception.*;

/**
 * Show specific client: also show previous notifications.
 */
class DoShowClient extends Command<Network> {

  DoShowClient(Network receiver) {
    super(Label.SHOW_CLIENT, receiver);
    addStringField("key", Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String key = stringField("key");
    try {
      _display.addLine(_receiver.clientToString(key));
      List<String> lines = _receiver.notiToString(key);
      for (String line : lines)
        _display.addLine(line);
      _display.display();
    }
    catch (ClientNotFoundException e) {
      throw new UnknownClientKeyException(key);
    }
    _display.display();
  }
}
