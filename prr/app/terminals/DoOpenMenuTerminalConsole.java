package prr.app.terminals;

import prr.core.Network;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import prr.app.terminal.Menu;


/**
 * Open a specific terminal's menu.
 */
class DoOpenMenuTerminalConsole extends Command<Network> {

  DoOpenMenuTerminalConsole(Network receiver, String terminalId) {
    super(Label.OPEN_MENU_TERMINAL, receiver);
    addStringField("id", "Introduza o Id desejado do terminal");
    //FIXME add command fields
  }

  @Override
  protected final void execute() throws CommandException {
    String id = stringField("id");
    new prr.app.terminal.Menu(_receiver, _receiver.findTerminal(id)).open();
  }
}
