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
  Network _context;

  DoShowTerminalBalance(Network context, Terminal terminal) {
    super(Label.SHOW_BALANCE, context, terminal);
    _context = context;
    addStringField("key", Message.terminalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String key = stringField("key");
    try{_display.addLine(Message.terminalPaymentsAndDebts(key, (long)_context.getTerminalPayment(key), (long)_context.getTerminalDebt(key)));   //FIXME i hope this works, idk if u have to replace _receiver for terminal
      _display.display();}
    catch(TerminalNotFoundException e){
      throw new UnknownTerminalKeyException(e.getTerminal());   //teacher no have constructor, idc
    }
  }
}
