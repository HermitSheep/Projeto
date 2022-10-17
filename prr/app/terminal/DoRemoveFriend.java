package prr.app.terminal;

import prr.core.Network;
import prr.core.Parser;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Remove friend.
 */
class DoRemoveFriend extends TerminalCommand {

  DoRemoveFriend(Network context, Parser.Terminal terminal) {
    super(Label.REMOVE_FRIEND, context, terminal);
    //FIXME add command fields
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
