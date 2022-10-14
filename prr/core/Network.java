package prr.core;

import java.io.Serializable;
import java.io.IOException;
import java.util.LinkedList;

import prr.core.exception.UnrecognizedEntryException;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;
  LinkedList<Terminal> _terminals;
  LinkedList<Client> _clients;
  LinkedList<Communication> _communications;


  // FIXME define attributes
  Public Network(){
    LinkedList<Terminal> _terminals = new LinkedList<Terminal>();
    LinkedList<Client> _clients = new LinkedList<Client>();
    LinkedList<Communication> _communications = new LinkedList<Communication>();
  }
  // FIXME define methods
  
  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   */
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    //FIXME implement method
  }

  public void registerClient(String name, int TaxNumber, String key) {
    //FIXME implement method
  }

  public void sendTextCommunication(Terminal from, String toKey, String msg) {
    //FIXME implement method
  }

  public void startInteractiveCommunication(Terminal from, String toKey, String type) {
    //FIXME implement method
  }

}

