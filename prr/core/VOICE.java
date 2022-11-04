package prr.core;

public class VOICE extends InteractiveCommunication{
  int duration;
  public VOICE(Terminal from, Terminal to) {
    super(from, to);
    _type = "VOICE";
  }

  protected long computeCost(TariffPlan plan) {
    long cost = plan.computeCost(_from.getClient(), this);
    _cost = cost;
    if (_from.isFriend(_to.getId()))
      _cost /=2;
    return _cost;
  }
}
