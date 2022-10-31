package prr.core;

import java.io.Serializable;
import java.util.*;
import prr.core.exception.InvalidTerminalIdException;

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
  protected String _client;
  protected List<Notification> _notifications;
  protected boolean _noComs;
  

  public Terminal(String id,  String client) {     //can an abstract class have a constructor? should it be private?~
    _id = id;
    _mode = TerminalMode.IDLE;
    _payments = 0;
    _debit = 0;
    _friends = new ArrayList<>();
    _client = client;
    _notifications = new ArrayList<>();
    _noComs = true;
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

  public TerminalMode getMode() {
    return _mode;
  }

  public double getPayments() {
     return _payments;
  }

  public double getDebit() {
     return _debit;
  }

  public String getClient() {
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

  public boolean isActive() {
    if (_mode == TerminalMode.OFF)
      return false;
    return true;
  }

  public String terminalToString() {
    String terminal = (this.getClass().getSimpleName().toUpperCase() + "|" + _id
                      + "|" + _client + "|" + _mode.toString()
                      + "|" + Math.round(_payments) + "|" + Math.round(_debit));
    if (!_friends.isEmpty()) {
      terminal += "|";
      Collections.sort(_friends);
      String friends = String.join(",", _friends);
      terminal += friends;
    }
    return terminal;
  }

  public void makeSMS(Terminal to, String mensage) {
    _noComs = false;
    // FIXME acho q é preciso criar uma comunicação e tal
  }

  protected void acceptSMS(Terminal from) {
    _noComs = false;
    // FIXME implementar comunicações
  }

  public void makeVoiceCall(Terminal to) {
    _noComs = false;
    // FIXME implementar comunicações
  }

  protected void acceptVoiceCall(Terminal to) {
    _noComs = false;
    // FIXME implementar comunicações
  }

  public abstract void makeVideoCall(Terminal to);

  protected abstract void acceptVideoCall(Terminal to);

  public void endOngoingComunications(int size){
    // FIXME implementar comunicações
  }

  public boolean setOnIdle(){
    _mode = TerminalMode.IDLE;
    return true;
  }

  public boolean setOnSilent(){
    if (_mode == TerminalMode.SILENSE)
      return false;
    _mode = TerminalMode.SILENSE;
    return true;
  }

  public boolean turnOff(){
    if (_mode == TerminalMode.OFF)
      return false;
    _mode = TerminalMode.OFF;
    return true;
  }
  
  /**
   * Checks if this terminal can end the current interactive communication.
   *
   * @return true if this terminal is busy (i.e., it has an active interactive communication) and
   *          it was the originator of this communication.
   **/
  public boolean canEndCurrentCommunication() {
    // FIXME add implementation code
    return _mode == TerminalMode.BUSY;
  }
  
  /**
   * Checks if this terminal can start a new communication.
   *
   * @return true if this terminal is neither off neither busy, false otherwise.
   **/
  public boolean canStartCommunication() {
    // FIXME add implementation code
    return !(_mode == TerminalMode.BUSY || _mode == TerminalMode.OFF);
  }

  public void addFriend(String term) {
    _friends.add(term);
  }
}
