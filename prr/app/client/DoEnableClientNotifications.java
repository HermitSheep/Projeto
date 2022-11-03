package prr.app.client;

import prr.core.Network;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.exception.ClientNotFoundException;
import prr.core.exception.StateNotChangedException;

/**
 * Enable client notifications.
 */
class DoEnableClientNotifications extends Command<Network> {

  DoEnableClientNotifications(Network receiver) {
    super(Label.ENABLE_CLIENT_NOTIFICATIONS, receiver);
    addStringField("key", Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String key = stringField("key");

    try {_receiver.enableNotification(key);}    
    catch (ClientNotFoundException e) {       
      throw new UnknownClientKeyException(e.getClient(), e);   
    }
    catch(StateNotChangedException e){
      _display.addLine(Message.clientNotificationsAlreadyEnabled());
      _display.display();
    }
  }
}
