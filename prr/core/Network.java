package prr.core;

import java.util.*;

import java.io.Serializable;
import java.io.IOException;

//import prr.core.Fancy;
//import prr.core.Basic;
//import prr.core.Client;
import prr.core.exception.*;

/**
 * Class Store implements a communications app.
 */
public class Network implements Serializable {

  private static final long serialVersionUID = 202208091753L;
  /** Serial number for serialization. */

  TreeMap<String, Terminal> _terminals = new TreeMap<String, Terminal>();
  TreeMap<String, Client> _clients = new TreeMap<String, Client>();
  TreeMap<Integer, Communication> _communications = new TreeMap<Integer, Communication>();

  public Network() {
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

  public String getTerminalType(Terminal term) {
    return term.getClass().getSimpleName();
  }

  public List<Client> getListclients() {
    Set<String> keys = _clients.keySet();
    List<Client> listClients = new ArrayList<Client>();
    for (String key : keys)
      listClients.add(_clients.get(key));
    return listClients;
  }

  public Client findClient(String key) throws ClientNotFoundException {
    if (!containsClient(key))
      throw new ClientNotFoundException(key);
    return _clients.get(key.toUpperCase());
  }

  public boolean containsClient(String cli) {
    if (_clients.get(cli.toUpperCase()) == null)
      return false;
    return true;
  }

  public void registerClient(String key, String name, int nif) throws ClientAlreadyExistsException {
    if (containsClient(key)) {
      throw new ClientAlreadyExistsException(key);
    }
    TariffPlan plan = new BasicPlan();
    Client cli = new Client(key, name, nif, plan);
    _clients.put(key.toUpperCase(), cli);
  }

  public List<String> clientsToString() {
    Set<String> keys = _clients.keySet();
    List<String> clis = new ArrayList<String>();
    for (String key : keys) {
      clis.add(_clients.get(key).toString());
    }
    return clis;
  }

  public String clientToString(String key) throws ClientNotFoundException {
    Client cli = _clients.get(key.toUpperCase());
    if (cli == null)
      throw new ClientNotFoundException(key);
    return cli.toString();
  }


  public void enableNotification(String key) throws ClientNotFoundException, StateNotChangedException{
    findClient(key).turnNotiOn();
  }

  public void disableNotification(String key) throws ClientNotFoundException, StateNotChangedException{
    findClient(key).turnNotiOff();
  }


  public long getClientPayment(String key) throws ClientNotFoundException{
    return findClient(key).getPayed();
  }

  public long getClientDebt (String key) throws ClientNotFoundException{
    return findClient(key).getDebt();
  }




  public List<Terminal> getListTerminals() {
    Set<String> ids = _terminals.keySet();
    List<Terminal> listTerminals = new ArrayList<Terminal>();
    for (String id : ids)
      listTerminals.add(_terminals.get(id));
    return listTerminals;
  }

  public Terminal findTerminal(String id) throws TerminalNotFoundException {
    if (_terminals.get(id) == null)
      throw new TerminalNotFoundException(id);
    return _terminals.get(id);
  }

  public List<String> terminalsToString() { // must return a fixed sollection of Strings so the App knows what it's
                                            // working with
    Set<String> ids = _terminals.keySet();
    List<String> terms = new ArrayList<String>();
    for (String id : ids) {
      terms.add(_terminals.get(id).terminalToString());
    }
    return terms;
  }

  public Terminal registerTerminal(String type, String id, String clientKey)
      throws TerminalAlreadyExistsException, ClientNotFoundException, InvalidTerminalIdException {
    if (_terminals.containsKey(id))
      throw new TerminalAlreadyExistsException(id);

    if (!containsClient(clientKey))
      throw new ClientNotFoundException(clientKey);
      
    if (type.equals("FANCY")) { // there might be a better way of checking this
      BASIC term = new BASIC(id, findClient(clientKey));
      try {
        term.validateId(id);
      } catch (InvalidTerminalIdException e) {
          throw new InvalidTerminalIdException(id, e);
      }
      _terminals.put(id, term);
      findClient(clientKey).addTerminal(term);
      return term;
    }
    
    else if (type.equals("BASIC")) {
      BASIC term = new BASIC(id, findClient(clientKey));
      try {
        term.validateId(id);
      } catch (InvalidTerminalIdException b) {
          throw new InvalidTerminalIdException(id, b);
      }
      _terminals.put(id, term);
      findClient(clientKey).addTerminal(term);
      return term;
    }
    return null;
  }

  public ArrayList<String> terminalsWithoutActivity() { // FIXME idk whether it's better to return simply List or
                                                        // ArrayList here
    ArrayList<String> res = new ArrayList<String>();
    for (Terminal term : _terminals.values())
      if (term.getNoComs())
        res.add(term.terminalToString());
    return res;
  }

  public double getTerminalPayment(String key) throws TerminalNotFoundException{
    return findTerminal(key).getPayments();
  }

  public double getTerminalDebt (String key) throws TerminalNotFoundException{
    return findTerminal(key).getDebt();
  }



  



  public void sendTextCommunication(Terminal from, String to, String msg) throws TerminalNotFoundException, InactiveTerminalException{
    Communication com = from.makeSMS(findTerminal(to), msg);
    _communications.put(com.getId(), com);
  }

  public void startInteractiveCommunication(Terminal from, String to, String type) throws TerminalNotFoundException, InactiveTerminalException, UnsuportedAtOrigin, UnsuportedAtDestination, StateNotChangedException{
    Communication com;
    if (type.equals("VOICE"))
      com = from.makeVoiceCall(findTerminal(to));
    else
      com = from.makeVideoCall(findTerminal(to));
    _communications.put(com.getId(), com);
  }

  public void endInteractiveCommunication(Terminal from, String to, double len) {
    
  }


  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException                if there is an IO erro while processing
   *                                    the text file
   */
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */ {
    Parser builder = new Parser(this);
    try {builder.parseFile(filename);}
    catch (IOException | UnrecognizedEntryException e) {
      throw new UnrecognizedEntryException(filename, e);
    }
  }

  public void addFriend(String term, String friend) throws TerminalNotFoundException {
    findTerminal(friend);
    findTerminal(term).addFriend(friend);
  }

  public void removeFriend(String term, String friend) throws TerminalNotFoundException{
    findTerminal(term).removeFriend(friend);
  }

  public void silenceTerminal(String term) throws TerminalNotFoundException, StateNotChangedException{
    findTerminal(term).set(TerminalMode.SILENSE); //I think we cant do that cause TerminalMode is in the Core not in the App but I am too tiered to change code right now but since im kind ill still tell you hwo to do it unless its future me cuase fuck you your a fucking pussy you pussy puss fuckboy femboy. You just need to give like a string or something and it should work. either that or it is better to just have seperate mathods for all the stuff and im a big dumb dumb???
  }

  public void turnOffTerminal(String term)throws TerminalNotFoundException, StateNotChangedException{
    findTerminal(term).set(TerminalMode.OFF);
  }

  public void turnIdleTerminal(String term)throws TerminalNotFoundException, StateNotChangedException{    //On isnt a terminal mode FIXME
    findTerminal(term).set(TerminalMode.IDLE);
  }

  public void turnBusyTerminal(String term)throws TerminalNotFoundException, StateNotChangedException{    //On isnt a terminal mode FIXME
    findTerminal(term).set(TerminalMode.BUSY);
  }
}
