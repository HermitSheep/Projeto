package prr.core;

import java.io.Serializable;

public abstract class TariffPlan implements Serializable {
  //private String _name;

  protected abstract double computeCost(Client cl, TextCommunication com);

  protected abstract double computeCost(Client cl, VoiceCommunication com);

  protected abstract double computeCost(Client cl, VideoCommunication com);
}
