package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;

import javax.naming.Context;

import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.exception.TerminalNotFoundException;
import prr.core.exception.UnavailableTerminalException;

/**
 * Command for sending a text communication.
 */
class DoSendTextCommunication extends TerminalCommand {

  DoSendTextCommunication(Network context, Terminal terminal) {
    super(Label.SEND_TEXT_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
    addStringField("sendTo", Message.terminalKey());
    addStringField("msg", Message.textMessage());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String sendTo = stringField("sendTo");
    String msg = stringField("msg");
    try {
      _network.sendTextCommunication(_receiver, sendTo, msg);
    }
    catch (TerminalNotFoundException e) {
      throw new UnknownTerminalKeyException(e.getTerminal());     //n sei se a mensagem operação inválida é mandada
    }
    catch (UnavailableTerminalException a) {
      _display.addLine(Message.destinationIsOff(sendTo));
      _display.display();
    }
  }
} 
