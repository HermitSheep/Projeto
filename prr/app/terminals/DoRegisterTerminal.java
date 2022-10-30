package prr.app.terminals;

import prr.core.Network;
import prr.app.exception.DuplicateTerminalKeyException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import prr.core.exception.*;

/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {

  DoRegisterTerminal(Network receiver) {
    super(Label.REGISTER_TERMINAL, receiver);
    String[] options = {"BASIC", "FANCY"};  //idk if basic and fancy count
    addStringField("id", Message.terminalKey());
    addOptionField("type", Message.terminalType(), options);
    addStringField("clientId", Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException, DuplicateTerminalKeyException, UnknownClientKeyException {
    String id = stringField("id");
    String type = stringField("type");
    String clientKey = stringField("clientId");

    try {_receiver.registerTerminal(type, id, clientKey);}
    catch (TerminalAlreadyExistsException e){
      throw new DuplicateTerminalKeyException(id);
    }
    catch (ClientNotFoundException b){
      throw new UnknownClientKeyException(clientKey);
    }
    catch (InvalidTerminalIdException a) {
      throw new InvalidTerminalKeyException(id);
    }
  }
}
