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

  public List<String> removeTerminals (int coms) {
    List<String> listTerms = new ArrayList<String>();
    Set<String> keys = _terminals.keySet();
    int numTerms = 0;
    for (String key : keys)
      if (_terminals.get(key).getNumComs() < coms) {
        listTerms.add(_terminals.get(key).terminalToString());
        _terminals.remove(key);
        numTerms ++;
      }
      String a = "" + numTerms;
      listTerms.add(a);
    return listTerms;
  }

  public List<String> ClientBestBasic () throws IOException {
    Set<String> keys = _clients.keySet();
    List<String> res = new ArrayList<String>();
    String best;
    int num = 0;
    int big = 0;
    for (String key : keys){
      for (Terminal term : _clients.get(key).getTerminals())
        if (term.getType().equals("BASIC"))
          num ++;
      if (num >= big) {
        best = key;
        big = num;
      }
    }
    try{
    res.add(_clients.get(best).toString());   //isto devia estar envolvido num try catch para prevenir crash, se for lançado erro o comando apanha e emite a mensagem não há clientes com terminais basic
    }
    catch (Exception a) {
      throw new IOException();
    }
    String a = "" + big;
    res.add(a);
    return res;
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

  public Communication findCom(int id) throws ComNotFoundException {
    if (!containsCom(id))
      throw new ComNotFoundException(id);
    return _communications.get(id);
  }

  public boolean containsCom(int id) {
    if (_communications.get(id) == null)
      return false;
    return true;
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

  public List<String> notiToString(String key){
    List<String> notis = new ArrayList<String>();
    for (Notification n: _clients.get(key.toUpperCase()).getNotifications()){
        notis.add(n.toString());
      }
      _clients.get(key.toUpperCase()).deleteNotifications();
    return notis;
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
      FANCY term = new FANCY(id, findClient(clientKey));
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

  public long getTerminalPayment(String key) throws TerminalNotFoundException{
    return (long)findTerminal(key).getPayments();
  }

  public long getTerminalDebt (String key) throws TerminalNotFoundException{
    return (long)findTerminal(key).getDebt();
  }



  



  public void sendTextCommunication(Terminal from, String to, String msg) throws TerminalNotFoundException, UnavailableTerminalException{
    Communication com = from.makeSMS(findTerminal(to), msg);
    _communications.put(com.getId(), com);
  }

  public void startInteractiveCommunication(Terminal from, String to, String type) throws TerminalNotFoundException, UnavailableTerminalException, UnsuportedAtOrigin, UnsuportedAtDestination, StateNotChangedException{
    Communication com;
    if (type.equals("VOICE"))
      com = from.makeVoiceCall(findTerminal(to));
    else{
      com = from.makeVideoCall(findTerminal(to));
    }
    _communications.put(com.getId(), com);
  }

  public long endInteractiveCommunication(Terminal term, int dur) throws StateNotChangedException, TerminalNotFoundException {
    if (dur <= 0)
      throw new TerminalNotFoundException(term.getId());
    return term.endOngoingComunication(dur);
  }

  public List<String> comsToClient(String cli) throws ClientNotFoundException {
    return findClient(cli).ComsMadeToString();
  }

  public List<String> comsFromClient(String cli) throws ClientNotFoundException {
    return findClient(cli).ComsReceivedToString();
  }

  public List<String> showAllComs() {
    Set<Integer> coms = _communications.keySet();
    List<String> lines = new ArrayList<String>();
    for (Integer com : coms) {
      lines.add(_communications.get(com).toString());
    }
    return lines;
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

  public void removeFriend(Terminal term, String friend) throws TerminalNotFoundException{
    term.removeFriend(friend);
  }

  public void silenceTerminal(String term) throws TerminalNotFoundException, StateNotChangedException{
    findTerminal(term).set(TerminalMode.SILENCE); //I think we cant do that cause TerminalMode is in the Core not in the App but I am too tiered to change code right now but since im kind ill still tell you hwo to do it unless its future me cuase fuck you your a fucking pussy you pussy puss fuckboy femboy. You just need to give like a string or something and it should work. either that or it is better to just have seperate mathods for all the stuff and im a big dumb dumb???
  }

  public void turnOffTerminal(String term)throws StateNotChangedException{
    try {
      findTerminal(term).set(TerminalMode.OFF);

    }
    catch (TerminalNotFoundException e) {}    //dont need to do anything  with it because it's only called from the terminal menu
  }

  public void turnIdleTerminal(String term)throws StateNotChangedException{    //On isnt a terminal mode FIXME
    try {
      findTerminal(term).set(TerminalMode.IDLE);

    }
    catch (TerminalNotFoundException a) {}
  }

  public void turnBusyTerminal(String term)throws StateNotChangedException{    //On isnt a terminal mode FIXME
    try {
      findTerminal(term).set(TerminalMode.BUSY);
    }
    catch (TerminalNotFoundException b) {}
  }
}
