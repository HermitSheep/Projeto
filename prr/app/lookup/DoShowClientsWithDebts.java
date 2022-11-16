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
    List<String> lines = _receiver.clientsByBalance(1);
    String[] argh = new String[20];
    for (int i = lines.size(); i-- > 0; ) {
      String line = lines.get(i);
      argh = line.split("\\|");
      if (Integer.parseInt(argh[7]) - Integer.parseInt(argh[8]) < 0)      //FIXME something is breaking here
        _display.addLine(line);
    }
    _display.display();
  }
}
