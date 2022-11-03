package prr.core;

import java.io.Serializable;

public abstract class TariffPlan implements Serializable {
  //private String _name;

  protected abstract double computeCost(Client cl, TEXT com);

  protected abstract double computeCost(Client cl, VOICE com);

  protected abstract double computeCost(Client cl, VIDEO com);
}
