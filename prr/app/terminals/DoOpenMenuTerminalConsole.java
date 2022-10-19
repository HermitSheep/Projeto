package prr.app.terminals;

import prr.core.Network;

import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import prr.app.terminal.Menu;
import prr.core.exception.TerminalNotFoundException;


/**
 * Open a specific terminal's menu.
 */
class DoOpenMenuTerminalConsole extends Command<Network> {

  DoOpenMenuTerminalConsole(Network receiver) {
    super(Label.OPEN_MENU_TERMINAL, receiver);
    addStringField("id", Message.terminalKey());
  }

  @Override
  protected final void execute() throws CommandException, UnknownTerminalKeyException {
    String id = stringField("id");
    try {new prr.app.terminal.Menu(_receiver, _receiver.findTerminal(id)).open();}
    catch (TerminalNotFoundException e) {
      throw new UnknownTerminalKeyException(id);
    }
  }
}
