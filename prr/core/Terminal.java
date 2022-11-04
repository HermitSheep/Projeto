package prr.core;

import java.io.Serializable;
import java.util.*;

import prr.core.exception.UnavailableTerminalException;
import prr.core.exception.InvalidTerminalIdException;
import prr.core.exception.UnsuportedAtOrigin;
import prr.core.exception.UnsuportedAtDestination;
import prr.core.exception.StateNotChangedException;
import prr.core.exception.TerminalNotFoundException;
import prr.core.exception.NoOngoigComException;
import prr.core.exception.ComNotFoundException;
import prr.core.exception.CommunicationAlreadyPayedException;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
public abstract class Terminal implements Serializable /* FIXME maybe addd more interfaces */{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;
  protected String _id;
  protected TerminalMode _mode;
  protected double _payments;
  protected double _debit;
  protected List<String> _friends;
  protected Client _client;                     //i should change this bacl to client, if it's usefull for the payment operations
  protected List<Notification> _notifications;
  protected List<Communication> _failedComs;  //For putting in the failled sommunications to send notifications
  protected boolean _noComs;
  protected List<Communication> _madeCommunications;
  protected List<Communication> _receivedCommunications;
  protected InteractiveCommunication _ongoingCom;
  protected String _type;
  

  public Terminal(String id,  Client client) {     //can an abstract class have a constructor? should it be private?~
    _id = id;
    _mode = TerminalMode.IDLE;
    _payments = 0;
    _debit = 0;
    _friends = new ArrayList<>();
    _client = client;
    _notifications = new ArrayList<>();
    _noComs = true;
    _failedComs = new ArrayList<>();
    _madeCommunications = new ArrayList<>();
    _receivedCommunications = new ArrayList<>();
  }

  public void validateId(String id) throws InvalidTerminalIdException{
    if (id.length() != 6)
      throw new InvalidTerminalIdException(id);
    if (!id.matches("[0-9]*"))
      throw new InvalidTerminalIdException(id);
  }

  public String getId() {
     return _id;
  }

  public String getType() {
     return _type;
  }

  public TerminalMode getMode() {
    return _mode;
  }

  public double getPayments() {
     return _payments;
  }

  public double getDebit() {
     return _debit;
  }

  public Client getClient() {
     return _client;
  }

  public List<String> getFriends() {
     return _friends;
  }

  public List<Notification> getNotifications() {
     return _notifications;
  }

  public boolean getNoComs() {
     return _noComs;
  }

  public double getDebt() {
     return _debit;
  }

  public boolean isActive() {
    if (_mode == TerminalMode.OFF)
      return false;
    return true;
  }

  public String terminalToString() {
    String terminal = (this.getClass().getSimpleName() + "|" + _id
                      + "|" + _client.getKey() + "|" + _mode.toString()
                      + "|" + Math.round(_payments) + "|" + Math.round(_debit));
    if (!_friends.isEmpty()) {
      terminal += "|";
      Collections.sort(_friends);
      String friends = String.join(",", _friends);
      terminal += friends;
    }
    return terminal;
  }

  protected void failedCom(Communication failure){
    _failedComs.add(failure);
  }

  private long computeCost(Communication c){
    long cost;
    cost = c.computeCost(_client.getPlan());
    _debit += cost;
    _client.addDebt(cost);
    return cost;
  }
  
  public Communication makeSMS(Terminal to, String msg) throws UnavailableTerminalException{
    _noComs = false;
    TEXT txt = new TEXT(msg, this, to);
    if (!this.isActive()){
      failedCom(txt);
      throw new UnavailableTerminalException(this.getId(), this.getMode());
    }
    to.acceptSMS(this, txt);
    computeCost(txt);
    _madeCommunications.add(txt);
    _client.addMadeCom(txt);
    to.getClient().addReceivedCom(txt);
    return txt;
  }

  protected void acceptSMS(Terminal from, TEXT txt) throws UnavailableTerminalException{
    if (!this.isActive()){
      failedCom(txt);
      throw new UnavailableTerminalException(this.getId(), this.getMode());
    }
    _noComs = false;
    _receivedCommunications.add(txt);
  }

  public Communication makeVoiceCall(Terminal to) throws UnavailableTerminalException, StateNotChangedException {
    _noComs = false;
    VOICE voi = new VOICE(this, to);
    set(TerminalMode.BUSY);
    voi.setOngoing(true);
    to.acceptVoiceCall(this, voi);
    _madeCommunications.add(voi);
    _ongoingCom = voi;
    _client.addMadeCom(voi);
    to.getClient().addReceivedCom(voi);
    return voi;
  }

  protected void acceptVoiceCall(Terminal from, VOICE voi) throws UnavailableTerminalException, StateNotChangedException{
    if (!this.canStartCommunication()){
      failedCom(voi);
      throw new UnavailableTerminalException(this.getId(), this.getMode());
    }
    set(TerminalMode.BUSY);
    _noComs = false;
    _receivedCommunications.add(voi);
    _ongoingCom = voi;
  }

  public abstract Communication makeVideoCall(Terminal to) throws UnsuportedAtOrigin, UnavailableTerminalException, UnsuportedAtDestination, StateNotChangedException;

  protected abstract void acceptVideoCall(Terminal from, VIDEO vid) throws UnsuportedAtDestination, StateNotChangedException, UnavailableTerminalException;

  public long endOngoingComunication(int dur) throws StateNotChangedException{
    long cost;
    _ongoingCom.setOngoing(false);
    _ongoingCom.setSize(dur);
    cost = computeCost(_ongoingCom);
    _ongoingCom = null;
    set(TerminalMode.IDLE);
    return cost;
  }

  public void addNotification(Notification n){
    _notifications.add(n);
  }

  public void deleteNotifications(){
    _notifications = new ArrayList<>();
  }

  public void sendNotification(TerminalMode changeTo){
    for (Communication c: _failedComs){
      Notification note = new Notification(c);
      note.setType(_mode, changeTo);                         //here lies progress
      if (!(!(c.getType().equals("TEXT")) && changeTo == TerminalMode.SILENSE))
        note.send();    //Maybe put an exception in case it dosent send here??
    }
  }

  public void set(TerminalMode changeTo) throws StateNotChangedException{
    sendNotification(changeTo);
    if(_mode == changeTo)
      throw new StateNotChangedException(this.getId(), _mode);
    _mode = changeTo;
    }

  public void payCom(Communication com) throws CommunicationAlreadyPayedException, ComNotFoundException, NoOngoigComException{
    if (_madeCommunications.contains(com)){
      if (!com.isPayed())
        throw new CommunicationAlreadyPayedException();
      if (com.getIsOngoing())
        throw new NoOngoigComException();
      _payments += com.getCost();
      _debit -= com.getCost();
      com.payCom();
    }
    else throw new ComNotFoundException(com.getId());
  }
   
  
  /**
   * Checks if this terminal can end the current interactive communication.
   *
   * @return true if this terminal is busy (i.e., it has an active interactive communication) and
   *          it was the originator of this communication.
   **/
  public boolean canEndCurrentCommunication() {
    if (!getIsOngoing())
      return false;
    if (_mode != TerminalMode.BUSY || _id != _ongoingCom.getFrom().getId())
      return false;
    return true;
  }
  
  /**
   * Checks if this terminal can start a new communication.
   *
   * @return true if this terminal is neither off neither busy, false otherwise.
   **/
  public boolean canStartCommunication() {
    return !(_mode == TerminalMode.BUSY || _mode == TerminalMode.OFF || getIsOngoing());
  }

  private boolean getIsOngoing(){
    return (_ongoingCom != null)?_ongoingCom.getIsOngoing():false;
  }

  public void addFriend(String term){
    
    if (term != this.getId())
      _friends.add(term);
  }

  public boolean isFriend(String term) {
    return _friends.contains(term);
  }
  
  public void removeFriend(String term) throws TerminalNotFoundException{
    if(!_friends.contains(term))
      throw new TerminalNotFoundException(term);
    _friends.remove(term); 
  }

  public String showOngoingCom() throws NoOngoigComException{
    if (_ongoingCom == null)
      throw new NoOngoigComException();
    return _ongoingCom.toString();
  }
}
