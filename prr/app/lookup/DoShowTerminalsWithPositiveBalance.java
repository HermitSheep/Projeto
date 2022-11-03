package prr.app.lookup;

import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;

/**
 * Show terminals with positive balance.
 */
class DoShowTerminalsWithPositiveBalance extends Command<Network> {

  DoShowTerminalsWithPositiveBalance(Network receiver) {
    super(Label.SHOW_TERMINALS_WITH_POSITIVE_BALANCE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    List<String> lines = _receiver.terminalsToString();
    for (String line : lines){
      String[] argh = line.split("\\|");
      if (Integer.parseInt(argh[4]) > Integer.parseInt(argh[5])) //pos 4 = Pagamento; pos 5 = Dividas
        _display.addLine(line);
    }
    _display.display();
  }
}
