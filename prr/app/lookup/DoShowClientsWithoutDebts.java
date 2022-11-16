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
    List<String> lines = _receiver.clientsByBalance(0);
    String[] argh = new String[20];
    for (int i = lines.size(); i-- > 0; ) {
      String line = lines.get(i);
      argh = line.split("\\|");
      if (Integer.parseInt(argh[7]) - Integer.parseInt(argh[8]) >= 0) //pos 8 = Pagamento; pos 9 = Dividas
        _display.addLine(line);
    }
    _display.display();
  }
}
