package prr.core;

import java.util.*;

import java.io.Serializable;
import java.io.IOException;

import prr.core.Fancy;
import prr.core.Basic;
import prr.core.Client;

import prr.core.exception.UnrecognizedEntryException;
import prr.core.exception.ClientAlreadyExistsException;
import prr.core.exception.*;

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

  public Terminal findTerminal(String id) throws TerminalNotFoundException{
    if (_terminals.get(id) == null)
      throw new TerminalNotFoundException(id);
    return _terminals.get(id);
  }

  public Client findClient(String key) throws ClientNotFoundException{
    if (_clients.get(key) == null)
      throw new ClientNotFoundException(key);
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

  public String clientToString(String key) throws ClientNotFoundException {
    Client cli = _clients.get(key);
    if (cli == null)
      throw new ClientNotFoundException(key);
    return cli.clientToString();
  }

  public boolean containsClient(String key) {
    key.toUpperCase();
    Set<String> keys = _clients.keySet();
    for (String key1 : keys) {
      if (key1.toUpperCase() == key)
        return true;
    }
    return false;
  }

  public boolean addClient(String key, String name, int nif) throws ClientAlreadyExistsException{
    if (!containsClient(key)) {
      throw new ClientAlreadyExistsException(key);
    }
    Client cli = new Client(key, name, nif);
    _clients.put(key, cli);
    return true;
  }

  public boolean addTerminal(String id, String type, String clientKey) throws TerminalAlreadyExistsException, ClientNotFoundException, InvalidTerminalIdException{
    if (!_terminals.containsKey(id))
      throw new TerminalAlreadyExistsException(id);
    if (!containsClient(clientKey))
      throw new ClientNotFoundException(clientKey);
    if (type.toUpperCase().equals("FANCY")){    //there might be a better way of checking this, and idk if it has to be fancyTerminal
      Fancy term = new Fancy(id, _clients.get(clientKey));
      try {term.validateId(id);}
      catch (InvalidTerminalIdException e) {
        throw new InvalidTerminalIdException(id, e);
      }
      _terminals.put(id, term);
      return true;
    }
    else if (type.toUpperCase().equals("BASIC")){
      Basic term = new Basic(id, _clients.get(clientKey));
      try {term.validateId(id);}
      catch (InvalidTerminalIdException b) {
        throw new InvalidTerminalIdException(id, b);
      }
      _terminals.put(id, term);
      return true;
    }
    return false;
  }

  public ArrayList<String> terminalsWithoutActivity() {   //FIXME idk whether it's better to return simply List or ArrayList here
    Set<String> ids = _terminals.keySet();
    ArrayList<String> res = new ArrayList<String>();
    for (String term : ids)
      if (_terminals.get(term).getNoComs())
        res.add(term);
    return res;
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

