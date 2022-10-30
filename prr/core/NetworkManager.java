package prr.core;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;

import prr.core.exception.ImportFileException;
import prr.core.exception.MissingFileAssociationException;
import prr.core.exception.UnavailableFileException;
import prr.core.exception.UnrecognizedEntryException;



//FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Manage access to network and implement load/save operations.
 */
public class NetworkManager {
  /** The network itself. */
  private Network _network = new Network();
  private String _filename;

  
  public Network getNetwork() {
    return _network;
  }

  public String getFilename() {
     return _filename;
  }
  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   */
  public void load(String filename) throws UnavailableFileException, IOException, ClassNotFoundException {
    try{
    ObjectInputStream textfile = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
    Network n = (Network)textfile.readObject();
    _network = n;
    _filename = filename;
    textfile.close();
  }  
  catch(FileNotFoundException e){
    throw new UnavailableFileException(filename);
  }
  }
  
  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void save() throws MissingFileAssociationException, FileNotFoundException, IOException {  //idk if the file is being saved properly, but i think not FIXME
    ObjectOutputStream save = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)));

    try {
      save.writeObject(_network);
    } 
    catch (FileNotFoundException e) {
      throw new MissingFileAssociationException();
    }
    catch (IOException e){
      e.printStackTrace();
    }
    finally{
      save.close();
    }
  }
  
  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void saveAs(String filename) throws ImportFileException {
    try {
      _filename = filename;
      save();
     } catch (MissingFileAssociationException | IOException/* FIXME maybe other exceptions */ e) {  //Nao sei o que fazer com as excessoes e ja estou bue cancado entao nao consigo lidar com isso hoje
       throw new ImportFileException(filename, e);
     }
  }
  
  /**
   * Read text input file and create domain entities..
   * 
   * @param filename name of the text input file
   * @throws ImportFileException
   */
  public void importFile(String filename) throws ImportFileException {
    try {
      _network.importFile(filename);
    } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(filename, e);
    }
  }
}
