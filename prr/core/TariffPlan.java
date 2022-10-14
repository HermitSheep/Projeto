package prr.core;

public abstract class TariffPlan {
  private String _name;

  protected abstract double computeCost(Client cl, TextCommunication com);

  protected abstract double computeCost(Client cl, VoiceCommunication com);

  protected abstract double computeCost(Client cl, VideoCommunication com);
}
