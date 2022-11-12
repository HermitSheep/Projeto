package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.exception.NoOngoigComException;
import prr.core.exception.ComNotFoundException;
import prr.core.exception.CommunicationAlreadyPayedException;

/**
 * Perform payment.
 */
class DoPerformPayment extends TerminalCommand {

  DoPerformPayment(Network context, Terminal terminal) {
    super(Label.PERFORM_PAYMENT, context, terminal);
    addIntegerField("com", Message.commKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    int com = integerField("com");

    try {_receiver.payCom(_network.findCom(com));}    
    catch (ComNotFoundException e) {       
      _display.addLine(Message.invalidCommunication());
      _display.display();
    }
    catch (NoOngoigComException|CommunicationAlreadyPayedException a) {
      
    }
  }
}
