package prr.core;

import java.io.Serializable;

public abstract class Communication implements Serializable{
  private static int _totalComs;     // because their scope is "all the users", this way the network might not need to have a list of coms too
  private int _id;
  private boolean _isPaid;
  double _cost;
  boolean _isOngoing;
  Terminal _to;
  Terminal _from;
  String _type;

  public Communication(Terminal from, Terminal to) {
    _totalComs++;
    _id = _totalComs;
    _from = from;
    _to = to;
  }

  public int getId() {
     return _id;
  }

  public double getCost() {
     return _cost;
  }

  public void setOngoing(boolean set) {
    _isOngoing = set;
  }

  public boolean getIsOngoing() {
    return _isOngoing;
  }

  public String getType() {
     return _type;
  }

  public Terminal getTo(){
    return _to;
  }

  public Terminal getFrom(){
    return _from;
  }

  public String toString() {
    String com = (this.getClass().getSimpleName() + "|" + _id + "|" + _from.getId() + "|" +
    _to.getId() + "|" + getSize() + "|" + String.valueOf(_cost) + "|");
    if (_isOngoing)
      com += "ONGOING";
    else {com += "FINISHED";}
    return com;
  }

  protected abstract double computeCost(TariffPlan plan);

  protected abstract int getSize();       //Must return 0 if it's ongoing (acording to the tests)
    //FIXME implement method
}
