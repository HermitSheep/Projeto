package prr.app.main;

import prr.core.NetworkManager;
//import prr.core.exception.ImportFileException;
import prr.app.exception.FileOpenFailedException;
//import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import prr.core.exception.UnavailableFileException;

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<NetworkManager> {

  DoOpenFile(NetworkManager receiver) {
    super(Label.OPEN_FILE, receiver);
    addStringField("file", Message.openFile());
  }

  @Override
  protected final void execute() throws CommandException {
    String file = stringField("file");
    try {
    _receiver.load(file);
    } 
    catch (Exception e) {
      throw new FileOpenFailedException(e);
    }
  }
}
