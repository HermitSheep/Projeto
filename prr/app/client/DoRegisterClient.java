package prr.app.client;

import prr.core.Network;
import prr.app.exception.DuplicateClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import prr.core.exception.ClientAlreadyExistsException;

/**
 * Register new client.
 */
class DoRegisterClient extends Command<Network> {

  DoRegisterClient(Network receiver) {
    super(Label.REGISTER_CLIENT, receiver);
    addStringField("key", "Introduza a chave desejada para o cliente: ");
    addStringField("name", "Introduza o nome do cliente: ");
    addIntegerField("nif", "Introduza o nif do cliente: ");
  }
  
  @Override
  protected final void execute() throws CommandException {
    String key = stringField("key");
    String name = stringField("name");
    int nif = integerField("nif");
    String message;

    try {_receiver.addClient(key, name, nif);}  //n sei se é suposto ser assim, ou com exceptions
    catch (ClientAlreadyExistsException e) {
      throw new DuplicateClientKeyException(key);
    }
  }
}
