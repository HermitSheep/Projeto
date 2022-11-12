package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show balance.
 */
class DoShowTerminalBalance extends TerminalCommand {

  DoShowTerminalBalance(Network context, Terminal terminal) {
    super(Label.SHOW_BALANCE, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
      String key = _receiver.getId();
      _display.addLine(Message.terminalPaymentsAndDebts(key, (long) _receiver.getPayments(), (long) _receiver.getDebt()));
      _display.display();
  }
}
