package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.exception.TerminalNotFoundException;
import prr.core.exception.InactiveTerminalException;
import prr.core.exception.UnsuportedAtOrigin;
import prr.core.exception.UnsuportedAtDestination;
import prr.core.exception.StateNotChangedException;

/**
 * Command for starting communication.
 */
class DoStartInteractiveCommunication extends TerminalCommand {
  Network _context;

  DoStartInteractiveCommunication(Network context, Terminal terminal) {
    super(Label.START_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> terminal.canStartCommunication());
    _context = context;
    String[] options = {"VOICE", "VIDEO"};
    addStringField("termTo", Message.terminalKey());
    addOptionField("type", Message.commType(), options);
  }
  
  @Override
  protected final void execute() throws CommandException {
    String termTo = stringField("termTo");
    String type = stringField("type");
    try {
      _context.startInteractiveCommunication(_receiver, termTo, type);
    }
    catch (TerminalNotFoundException e) {
      throw new UnknownTerminalKeyException(e.getTerminal());
    }
    catch (InactiveTerminalException a) {
      throw new DestinationTerminalOffException(termTo);
    }
    catch (UnsuportedAtOrigin|UnsuportedAtDestination|StateNotChangedException b) {}
  }
}
