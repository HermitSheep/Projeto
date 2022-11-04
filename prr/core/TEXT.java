package prr.core;

public class TEXT extends Communication{
  String _message;
  public TEXT(String message, Terminal from, Terminal to) {
    super(from, to);
    _message = message;
    _type = "TEXT";
  }

  protected long computeCost(TariffPlan plan) {
    long cost = plan.computeCost(_from.getClient(), this);
    _cost = cost;
    return cost;
  }

  protected int getSize() {
    return _message.length();
  }
}
