package prr.core;

public abstract class InteractiveCommunication extends Communication{
  private int _duration;

  public InteractiveCommunication(Terminal from, Terminal to) {
    super(from, to);
    _isOngoing = true;
  }

  public int getSize() {
    return _duration; 
  }

  public void setSize(int duration) {
    _duration = duration;
  }

  public void endCall(int duration) {
    _duration = duration;
    _isOngoing = false;
  }
}
