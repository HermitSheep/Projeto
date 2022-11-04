package prr.core;

public class VIDEO extends InteractiveCommunication{
  int duration;
  public VIDEO(Terminal from, Terminal to) {
    super(from, to);
    _type = "VIDEO";
  }

  protected long computeCost(TariffPlan plan) {
    long cost = plan.computeCost(_from.getClient(), this);
    _cost = cost;
    if (_from.isFriend(_to.getId()))
      _cost /=2;
    return _cost;
  }
}
