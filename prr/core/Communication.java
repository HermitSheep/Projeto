package prr.core;

import java.io.Serializable;

public abstract class Communication implements Serializable{
  private int _id;
  private boolean _isPaid;
  double _cost;
  boolean _isOngoing;

  public int getId() {
     return _id;
  }
  
  public boolean getIsPaid() {
     return _isPaid;
  }

  public double getCost() {
     return _cost;
  }

  public boolean getIsOngoing() {
     return _isOngoing;
  }

  public void makeSMS(Terminal to, String message) {
    //FIXME implement method
  }

  protected void acceptSMS(Terminal from) {
    //FIXME implement method
  }

  public void makeVoiceCall(Terminal to) {
    //FIXME implement method
  }

  public String toString() {
    //FIXME implement method
    return null;
  }

  protected double computeCost(TariffPlan plan) {
    //FIXME implement method
    return 0;
  }

  protected int getSize(){
    //FIXME implement method
    return 0;
  }
}
