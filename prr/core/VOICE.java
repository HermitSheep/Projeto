package prr.core;

public class VOICE extends InteractiveCommunication{
  int duration;
  public VOICE(Terminal from, Terminal to) {
    super(from, to);
    _type = "VOICE";
  }

  protected double computeCost(TariffPlan plan) {
    return plan.computeCost(_from.getClient(), this);
  }
}
