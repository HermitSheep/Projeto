package prr.app.client;

import prr.core.Network;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.exception.ClientNotFoundException;

/**
 * Show the payments and debts of a client.
 */
class DoShowClientPaymentsAndDebts extends Command<Network> {

  DoShowClientPaymentsAndDebts(Network receiver) {
    super(Label.SHOW_CLIENT_BALANCE, receiver);
    addStringField("key", Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String key = stringField("key");
    try{_display.addLine(Message.clientPaymentsAndDebts(key, _receiver.getClientPayment(key), _receiver.getClientDebt(key)));
        _display.display();}
    catch(ClientNotFoundException e){
      throw new UnknownClientKeyException(e.getClient(), e);
    }
  }
}
