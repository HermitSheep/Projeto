package prr.core;

import java.io.Serializable;

public abstract class TariffPlan implements Serializable {
  //private String _name;

  protected abstract long computeCost(Client cl, TEXT com);

  protected abstract long computeCost(Client cl, VOICE com);

  protected abstract long computeCost(Client cl, VIDEO com);
}
