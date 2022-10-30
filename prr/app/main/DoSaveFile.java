package prr.app.main;

import prr.core.NetworkManager;
//import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;

import prr.core.exception.*;
//import java.io.FileNotFoundException;
//import java.io.IOException;
import prr.app.exception.*;
import java.io.IOException;

/**
 * Command to save a file.
 */
class DoSaveFile extends Command<NetworkManager> {
  String filename;

  DoSaveFile(NetworkManager receiver) {
    super(Label.SAVE_FILE, receiver);
    filename = receiver.getFilename();
    if (filename == null)
      addStringField("file", Message.newSaveAs());

  }

  @Override
  protected final void execute() throws FileOpenFailedException {
    String file;
    try {
      if (filename == null) {
        file = stringField("file");
        _receiver.saveAs(file);
      }
      else {
        _receiver.save();
        _display.addLine(Message.saveAs());
        _display.display();
      }
    } 
    catch (ImportFileException | MissingFileAssociationException | IOException e) {  //fileNotFound extends IOException
        throw new FileOpenFailedException(e);
    }
  }
}
