package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.exception.TerminalNotFoundException;
import prr.app.exception.UnknownTerminalKeyException;

/**
 * Show balance.
 */
class DoShowTerminalBalance extends TerminalCommand {

  DoShowTerminalBalance(Network context, Terminal terminal) {
    super(Label.SHOW_BALANCE, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    try{
      String key = _receiver.getId();
      _display.addLine(Message.terminalPaymentsAndDebts(key, _network.getTerminalPayment(key), _network.getTerminalDebt(key)));
      _display.display();}
    catch(TerminalNotFoundException e){
      throw new UnknownTerminalKeyException(e.getTerminal()); //this error should never be run, terminal gets checked for entering this menu
    }
  }
}
