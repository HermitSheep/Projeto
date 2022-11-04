package prr.app.lookup;

import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;

/**
 * Command for showing all communications.
 */
class DoShowAllCommunications extends Command<Network> {

  DoShowAllCommunications(Network receiver) {
    super(Label.SHOW_ALL_COMMUNICATIONS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    List<String> lines = _receiver.showAllComs();
    for (String line : lines)
      _display.addLine(line);
    _display.display();
  }
}
