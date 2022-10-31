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
  String filename = _receiver.getFilename();
  DoSaveFile(NetworkManager receiver) {
    super(Label.SAVE_FILE, receiver);
    System.out.println("started saving: " + filename);
    System.out.println("went for save as");
    addStringField("file", Message.newSaveAs());

    System.out.println("leaving constructor: " + filename);
  }

  @Override
  protected final void execute() throws FileOpenFailedException {
    System.out.println("entered execute");
    String filename = _receiver.getFilename();
    try {
      if (filename == null) {
        filename = stringField("file");
        _receiver.saveAs(filename);
      }
      else {
        _receiver.save();
      }
    } 
    catch (ImportFileException | MissingFileAssociationException | IOException e) {  //fileNotFound extends IOException
        throw new FileOpenFailedException(e);
    }
    finally {System.out.println("finished saving");}
  }
}
