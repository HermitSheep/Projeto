package prr.app.main;

import prr.core.NetworkManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.forms.*;
import prr.app.exception.FileOpenFailedException;
import prr.core.exception.ImportFileException;
import prr.core.exception.MissingFileAssociationException;


/**
 * Command to save a file.
 */
class DoSaveFile extends Command<NetworkManager> {
  String filename = _receiver.getFilename();
  Form form = new Form();
  DoSaveFile(NetworkManager receiver) {
    super(Label.SAVE_FILE, receiver);
  }

  @Override
  protected final void execute() throws FileOpenFailedException {
  String filename = _receiver.getFilename();
    try {
      form.clear();
      if (filename == null) {
        form.addStringField("file", Message.newSaveAs());
        form.parse(true);
        _receiver.saveAs(form.stringField("file"));
      }
      else
        _receiver.save();
    } 
    catch (ImportFileException|MissingFileAssociationException e) {
        throw new FileOpenFailedException(e);
    }
  }
}
