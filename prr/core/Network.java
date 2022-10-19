package prr.core;

import java.util.*;

import java.io.Serializable;
import java.io.IOException;

import prr.core.Fancy;
import prr.core.Basic;
import prr.core.Client;
import prr.core.exception.UnrecognizedEntryException;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

  private static final long serialVersionUID = 202208091753L; /** Serial number for serialization. */

  TreeMap<String, Terminal> _terminals = new TreeMap<String, Terminal>();
  TreeMap<String, Client> _clients = new TreeMap<String, Client>();
  TreeMap<Integer, Communication> _communications = new TreeMap<Integer, Communication>();

  public Network(){
  }

  public TreeMap<String, Terminal> getTerminals() {
     return _terminals;
  }

  public TreeMap<String, Client> getClients() {
     return _clients;
  }

  public TreeMap<Integer, Communication> getCommunications() {
     return _communications;
  }

  public Terminal findTerminal(String id) {
    return _terminals.get(id);
  }

  public Client findClient(String key) {
    return _clients.get(key);
  }

  public List<String> terminalsToString() {   //must return a fixed sollection of Strings so the App knows what it's working with
    Set<String> ids = _terminals.keySet();
    List<String> terms = new ArrayList<String>();
    for(String id : ids){
        terms.add(_terminals.get(id).terminalToString());
    }
    return terms;
  }

  public List<String> clientsToString() {
    Set<String> keys = _clients.keySet();
    List<String> clis = new ArrayList<String>();
    for(String key : keys){
        clis.add(_clients.get(key).clientToString());
    }
    return clis;
  }

  public String clientToString(String key) {
    return _clients.get(key).clientToString();
  }

  public boolean addClient(String key, String name, String nif) {
    if (!_clients.containsKey(key))   //FIXME exception for duplicate object?
      return false;
    Client cli = new Client(key, name, nif);
    _clients.put(key, cli);
    return true;
  }

  public boolean addTerminal(String id, String type, String clientKey) {
    if (!_terminals.containsKey(id))   //FIXME exception for duplicate object?
      return false;
    if (!_clients.containsKey(clientKey))   //FIXME exception for the client not existing
      return false;
    if (type.equals("Fancy") || type.equals("fancy")){    //there might be a better way of checking this, and idk if it has to be fancyTerminal
      Fancy term = new Fancy(id, _clients.get(clientKey));
      _terminals.put(id, term);
      return true;
    }
    else if (type.equals("Basic") || type.equals("basic")){
      Basic term = new Basic(id, _clients.get(clientKey));
      _terminals.put(id, term);
      return true;
    }
    // FIXMEelse throw invalid terminal type exception
    return false;
  }

  
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

