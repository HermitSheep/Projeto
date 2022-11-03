package prr.core;

public class VIDEO extends InteractiveCommunication{
  int duration;
  public VIDEO(Terminal from, Terminal to) {
    super(from, to);
    _type = "VIDEO";
  }

  protected double computeCost(TariffPlan plan) {
    return plan.computeCost(_from.getClient(), this);
  }
}
