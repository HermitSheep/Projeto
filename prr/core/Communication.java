package core;

public class Communication {
  private int _id;
  private boolean _isPaid;
  double _cost;
  boolean _isOngoing;

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
  }

  protected double computeCost(TariffPlan plan) {
    //FIXME implement method
  }

  protected int getSize(){
    //FIXME implement method
  }
}
