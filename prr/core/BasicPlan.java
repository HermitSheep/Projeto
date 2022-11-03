package prr.core;

public class BasicPlan extends TariffPlan{
  public BasicPlan(){}

  protected double computeCost(Client cli, TEXT com) {
    int len = com.getSize();
    ClientLevel lev = cli.getLevel();
    if (len < 50) {
      if (lev == ClientLevel.NORMAL || lev == ClientLevel.GOLD)
        return 10;
      else {return 0;}
    }
    else if (len < 100) {
      if (lev == ClientLevel.NORMAL)
        return 16;
      else if (lev == ClientLevel.GOLD)
        return 10;
      else {return 4;}
    }
    else {
      if (lev == ClientLevel.NORMAL || lev == ClientLevel.GOLD)
        return 2*len;
      else {return 4;}
    }
  }

  protected double computeCost(Client cli, VOICE com) {
    int len = com.getSize();
    ClientLevel lev = cli.getLevel();
    if (lev == ClientLevel.NORMAL)
      return 20*len;
    else {return 10*len;}
  }

  protected double computeCost(Client cli, VIDEO com){
    int len = com.getSize();
    ClientLevel lev = cli.getLevel();
    if (lev == ClientLevel.NORMAL)
      return 30*len;
    else if (lev == ClientLevel.GOLD)
      return 20*len;
    else {return 10*len;}
  }
}
