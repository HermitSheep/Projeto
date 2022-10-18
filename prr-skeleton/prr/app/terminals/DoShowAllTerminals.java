package prr.app.terminals;

import java.util.List;

import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all terminals.
 */
class DoShowAllTerminals extends Command<Network> {

  DoShowAllTerminals(Network receiver) {
    super(Label.SHOW_ALL_TERMINALS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    List<String> lines = _receiver.terminalsToString();
    for (String line : lines)
      _display.addLine(line);
    _display.display();
  }
}
