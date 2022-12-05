package prr.app.lookup;

import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;

/**
 * Show clients with positive balance.
 */
class DoShowClientsWithoutDebts extends Command<Network> {

  DoShowClientsWithoutDebts(Network receiver) {
    super(Label.SHOW_CLIENTS_WITHOUT_DEBTS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    List<String> lines = _receiver.clientsToString();
    for (String line : lines){
      String[] argh = line.split("\\|");
      if (Integer.parseInt(argh[8]) - Integer.parseInt(argh[9]) >= 0) //pos 8 = Pagamento; pos 9 = Dividas
        _display.addLine(line);
    }
    _display.display();
  }
}
