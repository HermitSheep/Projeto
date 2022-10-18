package prr.app.terminals;

import prr.core.Network;
import prr.app.exception.DuplicateTerminalKeyException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {

  DoRegisterTerminal(Network receiver) {
    super(Label.REGISTER_TERMINAL, receiver);
    addIntegerField("id", "Introduza o Id desejado do terminal");
    addStringField("type", "Introduza o tipo de terminal desejado: ");
    addIntegerField("clientId", "Introduza o Id do cliente associado: ");
  }

  @Override
  protected final void execute() throws CommandException {
    String id = stringField("id");
    String type = stringField("type");
    String clientId = stringField("clientId");
    String message;

    if (_receiver.addTerminal(id, type, clientId))  //n sei se é suposto ser assim, ou com exceptions
      message = "Terminal registado.";              //copiei o exemplo
    else
      message = "Terminal não adicionado";
    
    _display.popup(message);
  }
}
