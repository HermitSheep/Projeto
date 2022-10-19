package prr.app.client;

import prr.core.Network;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show specific client: also show previous notifications.
 */
class DoShowClient extends Command<Network> {

  DoShowClient(Network receiver) {
    super(Label.SHOW_CLIENT, receiver);
    addStringField("key", "Introduza a chave desejada para o cliente: ");
  }
  
  @Override
  protected final void execute() throws CommandException {
    String key = stringField("key");
    String line = _receiver.clientToString(key);
    _display.addLine(line);
    _display.display();
  }
}
