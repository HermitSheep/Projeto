package prr.app.terminal;

import prr.core.Network;
import prr.core.Parser;
import pt.tecnico.uilib.menus.CommandException;
// Add more imports if needed

/**
 * Perform payment.
 */
class DoPerformPayment extends TerminalCommand {

  DoPerformPayment(Network context, Parser.Terminal terminal) {
    super(Label.PERFORM_PAYMENT, context, terminal);
    //FIXME add command fields
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
