package prr.core;

public class TEXT extends Communication{
  String _message;
  public TEXT(String message, Terminal from, Terminal to) {
    super(from, to);
    _message = message;
    _type = "TEXT";
  }

  protected double computeCost(TariffPlan plan) {
    return plan.computeCost(_from.getClient(), this);
  }

  protected int getSize() {
    return _message.length();
  }
}
