package prr.core;

import java.io.Serializable;
import java.util.*;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
public abstract class Terminal implements Serializable /* FIXME maybe addd more interfaces */{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;
  private final String _id;
  private TerminalMode _mode;
  private double _payments;
  private double _debit;
  private List<Terminal> _friends;
  private Client _client;
  

  Terminal(String id, String type, Client client) {     //can an abstract class have a constructor? should it be private?
    _id = id;
    //FIXME type
    _mode = TerminalMode.OFF;
    _payments = 0;
    _debit = 0;
    ArrayList<Terminal> _friends = new ArrayList<Terminal>();
    _client = client;
  }

  private String idGetter() {
     return _id;
  }

  private TerminalMode modeGetter() {
    return _mode;
  }

  private double paymentsGetter() {
     return _payments;
  }

  private double debitGetter() {
     return _debit;
  }

  private Client clientGetter() {
     return _client;
  }

  private List<Terminal> friendsGetter() {
     return _friends;
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
