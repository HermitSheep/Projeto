package core;

import java.io.Serializable;
import java.util.LinkedList;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable /* FIXME maybe addd more interfaces */{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;
  private final String _id;
  private String _mode;
  private String _oldState;
  private double _payments;
  private double _debit;
  LinkedList<Terminal> _friends;

  public abstract Terminal(String id, String type) {
    _id = id;
    _type = type;
    _mode = "off";
    _oldState = "off";
    _payments = 0;
    _debit = 0;
    LinkedList<Terminal> _friends = new LinkedList<Terminal>();
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
    _friends.addLast(term);
  }
}
