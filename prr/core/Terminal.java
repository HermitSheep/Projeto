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
  private String _id;
  private TerminalMode _mode;
  private double _payments;
  private double _debit;
  private List<String> _friends;
  private Client _client;
  private List<Notification> _notifications;
  

  public Terminal(String id, Client client) {     //can an abstract class have a constructor? should it be private?~
    _id = id;
    _mode = TerminalMode.OFF;
    _payments = 0;
    _debit = 0;
    _friends = new ArrayList<>();
    _client = client;
    _notifications = new ArrayList<>();
  }

  public void validateId(String id) throws InvalidTerminalIdException{
    if (id.length() != 6)
      throw new InvalidTerminalIdException(id);
    try {String.valueOf(id);}
    catch (Exception e) {throw new InvalidTerminalIdException(id);}
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

  public Client getClient() {
     return _client;
  }

  public List<String> getFriends() {
     return _friends;
  }

  public List<Notification> getNotifications() {
     return _notifications;
  }

  public boolean isActive() {
    if (_mode == TerminalMode.OFF)
      return false;
    return true;
  }

  public String terminalToString() {
    String terminal = (this.getClass().getSimpleName() + "|" + _id
                      + "|" + _client.getName() + "|" + _mode.toString()
                      + "|" + Double.toString(_payments) + "|" + Double.toString(_debit));
    terminal += "|";
    Collections.sort(_friends);
    String friends = String.join(",", _friends);
    terminal += friends;
    return terminal;
  }

  public void makeSMS(Terminal to, String mensage) {
    // FIXME acho q é preciso criar uma comunicação e tal
  }

  protected void acceptSMS(Terminal from) {
    // FIXME implementar comunicações
  }

  public void makeVoiceCall(Terminal to) {
    // FIXME implementar comunicações
  }

  protected void acceptVoiceCall(Terminal to) {
    // FIXME implementar comunicações
  }

  public abstract void makeVideoCall(Terminal to);

  protected abstract void acceptVideoCall(Terminal to);

  public void endOngoingComunications(int size){
    // FIXME implementar comunicações
  }

  public boolean setOnIdle(){
    // FIXME implementar comunicações
  }

  public boolean setOnSilent(){
    // FIXME implementar comunicações
  }

  public boolean turnOff(){
    // FIXME implementar comunicações
  }
  
  /**
   * Checks if this terminal can end the current interactive communication.
   *
   * @return true if this terminal is busy (i.e., it has an active interactive communication) and
   *          it was the originator of this communication.
   **/
  public boolean canEndCurrentCommunication() {
    // FIXME add implementation code
  }
  
  /**
   * Checks if this terminal can start a new communication.
   *
   * @return true if this terminal is neither off neither busy, false otherwise.
   **/
  public boolean canStartCommunication() {
    // FIXME add implementation code
  }

  public void addFriend(Terminal term) {
    //FIXME
  }
}
