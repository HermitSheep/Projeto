package prr.app.lookup;

import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;

/**
 * Show clients with negative balance.
 */
class DoShowClientsWithDebts extends Command<Network> {

  DoShowClientsWithDebts(Network receiver) {
    super(Label.SHOW_CLIENTS_WITH_DEBTS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    List<String> lines = _receiver.clientsToString();
    String[] argh;
    for (String line : lines){
      argh = line.split("\\|");
      if ((Integer.parseInt(argh[8]) - Integer.parseInt(argh[9]) < 0))
        _display.addLine(line);
    }
    _display.display();
  }
}
